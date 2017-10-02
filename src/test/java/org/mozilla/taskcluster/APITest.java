package org.mozilla.taskcluster;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Certificate;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.index.Index;
import org.mozilla.taskcluster.client.queue.Queue;
import org.mozilla.taskcluster.client.queue.TaskDefinitionRequest;
import org.mozilla.taskcluster.client.queue.TaskStatusResponse;

import com.google.gson.Gson;

import net.iharder.Base64;

public class APITest {

    private static Logger log = Logger.getGlobal();

    /**
     * Generates a 22 character random slugId that is url-safe ([0-9a-zA-Z_\-]*)
     */
    public static String slug() {
        UUID uuid = UUID.randomUUID();
        long hi = uuid.getMostSignificantBits();
        long lo = uuid.getLeastSignificantBits();
        ByteBuffer raw = ByteBuffer.allocate(16);
        raw.putLong(hi);
        raw.putLong(lo);
        byte[] rawBytes = raw.array();
        return Base64.encodeBytes(rawBytes).replace('+', '-').replace('/', '_').substring(0, 22);
    }

    /**
     * This is a silly test that looks for the latest mozilla-inbound linux64 debug
     * build and asserts that it must have a created time between a year ago and an
     * hour in the future.
     *
     * Could easily break at a point in the future, e.g. if this index route
     * changes, at which point we can change to something else.
     *
     * Note, no credentials are needed, so this can be run even on travis-ci.org,
     * for example.
     */
    @Test
    public void findLatestLinux64DebugBuild() {
        Index index = new Index();
        Queue queue = new Queue();
        try {
            String taskId = index.findTask("gecko.v2.mozilla-inbound.latest.firefox.linux64-debug").responsePayload.taskId;
            Date created = queue.task(taskId).responsePayload.created;

            // calculate time an hour in the future to allow for clock drift
            Date now = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.HOUR, 1);
            Date inAnHour = c.getTime();
            c.setTime(now);
            c.add(Calendar.YEAR, -1);
            Date aYearAgo = c.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss Z");
            System.out.println("=> Task " + taskId + " was created on " + sdf.format(created));
            Assert.assertTrue(created.before(inAnHour));
            Assert.assertTrue(created.after(aYearAgo));

        } catch (APICallFailure e) {
            e.printStackTrace();
            Assert.fail("Exception thrown");
        }

    }

    /**
     * Tests whether it is possible to define a task against the production
     * Queue.
     */
    @Test
    public void testDefineTask() {
        String clientId = System.getenv("TASKCLUSTER_CLIENT_ID");
        String accessToken = System.getenv("TASKCLUSTER_ACCESS_TOKEN");
        String certificate = System.getenv("TASKCLUSTER_CERTIFICATE");
        Assume.assumeFalse(clientId == null || clientId == "" || accessToken == null || accessToken == "");
        Queue queue = new Queue(new Credentials(clientId, accessToken, certificate));
        String taskId = slug();
        TaskDefinitionRequest td = new TaskDefinitionRequest();
        td.created = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(td.created);
        c.add(Calendar.DATE, 1);
        td.deadline = c.getTime();
        td.expires = td.deadline;
        Map<String, Object> index = new HashMap<String, Object>();
        index.put("rank", 12345);
        Map<String, Object> extra = new HashMap<String, Object>();
        extra.put("index", index);
        td.extra = extra;
        td.metadata = td.new MetaData();
        td.metadata.description = "Stuff";
        td.metadata.name = "[TC] Pete";
        td.metadata.owner = "pmoore@mozilla.com";
        td.metadata.source = "http://somewhere.com/";
        Map<String, Object> features = new HashMap<String, Object>();
        features.put("relengAPIProxy", true);
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("features", features);
        td.payload = payload;
        td.provisionerId = "win-provisioner";
        td.retries = 5;
        td.routes = new String[] { "tc-treeherder.mozilla-inbound.bcf29c305519d6e120b2e4d3b8aa33baaf5f0163",
                "tc-treeherder-stage.mozilla-inbound.bcf29c305519d6e120b2e4d3b8aa33baaf5f0163" };
        td.schedulerId = "junit-test-scheduler";
        td.scopes = new String[] {};

        Map<String, Object> tags = new HashMap<String, Object>();
        tags.put("createdForUser", "cbook@mozilla.com");
        td.tags = tags;
        td.priority = "high";
        td.taskGroupId = "dtwuF2n9S-i83G37V9eBuQ";
        td.workerType = "win2008-worker";

        try {
            CallSummary<TaskDefinitionRequest, TaskStatusResponse> cs = queue.defineTask(taskId, td);
            Assert.assertEquals(cs.requestPayload.provisionerId, "win-provisioner");
            Assert.assertEquals(cs.responsePayload.status.schedulerId, "junit-test-scheduler");
            Assert.assertEquals(cs.responsePayload.status.retriesLeft, 5);
            Assert.assertEquals(cs.responsePayload.status.state, "unscheduled");
        } catch (APICallFailure e) {
            e.printStackTrace();
            Assert.fail("Exception thrown");
        }

        System.out.println("=> Task https://queue.taskcluster.net/v1/task/" + taskId + " created successfully");
    }

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /*
     * This test checks static test cases of generating temporary credentials by
     * looking up known results for a range of test cases.
     *
     * TODO: Add tests that call auth endpoints: https://bugzilla.mozilla.org/show_bug.cgi?id=1257520
     */
    @Test
    public void createTemporaryCredentials() {
        BufferedReader br = null;
        try {
            Gson gson = new Gson();
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/testcases.json")));
            TempCredsTestCase[] testCases = gson.fromJson(br, TempCredsTestCase[].class);

            for (TempCredsTestCase tc : testCases) {

                log.info("Testing " + tc.description);
                Date start = sdf.parse(tc.start);
                Date expiry = sdf.parse(tc.expiry);

                Credentials permCreds = new Credentials(tc.permCreds.clientId, tc.permCreds.accessToken);
                Credentials tempCreds = permCreds.createTemporaryCredentials(tc.tempCredsName, tc.tempCredsScopes,
                        start, expiry);
                Certificate cert = tempCreds.getCertificate();

                cert.seed = tc.seed;
                // need to generate access token using fixed seed
                tempCreds.accessToken = Credentials.generateTemporaryAccessToken(permCreds.accessToken, cert.seed);
                // need to recalculate signature, as seed was updated
                cert.generateSignature(permCreds.accessToken, tempCreds.clientId);
                // update credentials with updated certificate
                tempCreds.certificate = cert.toString();
                Assert.assertEquals(tc.expectedTempCreds.clientId, tempCreds.clientId);
                Assert.assertEquals(tc.expectedTempCreds.accessToken, tempCreds.accessToken);
                Assert.assertEquals(tc.expectedTempCreds.certificate, tempCreds.certificate);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown");
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
