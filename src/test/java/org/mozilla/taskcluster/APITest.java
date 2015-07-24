package org.mozilla.taskcluster;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.queue.Queue;
import org.mozilla.taskcluster.client.queue.TaskDefinition;
import org.mozilla.taskcluster.client.queue.TaskStatusResponse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class APITest extends TestCase {

	public APITest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(APITest.class);
	}

	/**
	 * Tests whether it is possible to define a task against the production
	 * Queue.
	 */
	public void testDefineTask() {
		Queue queue = new Queue(System.getenv("TASKCLUSTER_CLIENT_ID"), System.getenv("TASKCLUSTER_ACCESS_TOKEN"));
		UUID uuid = UUID.randomUUID();
		long hi = uuid.getMostSignificantBits();
		long lo = uuid.getLeastSignificantBits();
		String taskId = Base64.getEncoder().withoutPadding()
				.encodeToString(ByteBuffer.allocate(16).putLong(hi).putLong(lo).array()).replace('+', '-')
				.replace('/', '_');
		TaskDefinition td = new TaskDefinition();
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
		td.scopes = new String[] { "docker-worker:image:taskcluster/builder:0.5.6",
				"queue:define-task:aws-provisioner-v1/build-c4-2xlarge" };

		Map<String, Object> tags = new HashMap<String, Object>();
		tags.put("createdForUser", "cbook@mozilla.com");
		td.tags = tags;
		td.taskGroupId = "dtwuF2n9S-i83G37V9eBuQ";
		td.workerType = "win2008-worker";

		try {
			CallSummary<TaskDefinition, TaskStatusResponse> cs = queue.defineTask(taskId, td);
			assertEquals(cs.requestPayload.provisionerId, "win-provisioner");
			assertEquals(cs.responsePayload.status.schedulerId, "junit-test-scheduler");
			assertEquals(cs.responsePayload.status.retriesLeft, 5);
			assertEquals(cs.responsePayload.status.state, "unscheduled");
		} catch (APICallFailure e) {
			fail("Exception thrown");
			e.printStackTrace();
		}
	}
}
