package org.mozilla.taskcluster;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.index.Index;
import org.mozilla.taskcluster.client.queue.Queue;
import org.mozilla.taskcluster.client.queue.TaskDefinitionRequest;
import org.mozilla.taskcluster.client.queue.TaskStatusResponse;
import org.mozilla.taskcluster.client.TemporaryCredentials;

import net.iharder.Base64;

public class APITest {

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
     * This is a silly test that looks for the latest mozilla-central buildbot linux64 l10n build
     * and asserts that it must have a created time between a year ago and an hour in the future.
     *
     * Could easily break at a point in the future, at which point we can change to something else.
     */
    @Test
    public void findLatestBuildbotTask() {
        Index index = new Index();
        Queue queue = new Queue();
        try {
            String taskId = index.findTask("buildbot.branches.mozilla-central.linux64.l10n").responsePayload.taskId;
            Date created = queue.task(taskId).responsePayload.created;

            // calculate time an hour in the future to allow for clock drift
            Date now = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.HOUR, 1);
            Date inAnHour = c.getTime();
            c.setTime(now);;
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
        Queue queue;
        if (certificate == null || certificate == "") {
            queue = new Queue(clientId, accessToken);
        } else {
            queue = new Queue(clientId, accessToken, certificate);
        }
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
        td.metadata = td.new Metadata();
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

    /*
     * This just checks if the TemporaryCredentials works
     */
    @Test
    public void createTemporaryCredentials(){
      try{
        String[] tempScopes = new String[2];
        Date start = new Date();
        Date expiry = new Date(start.getTime() + 2*24*60*60*1000);
        tempScopes[0] = "scopeA";
        tempScopes[1] = "scopeB";
        String temp = TemporaryCredentials.createCredentials(
        "clientId","tokenABCDEFGH", tempScopes, start, expiry
        );
        //System.out.println("=>unnamed credentials "+temp);
      }catch(Exception e){
        e.printStackTrace();
        Assert.fail("Exception thrown");
      }
      System.out.println("=> TemporaryCredentials\n");
    }

}
