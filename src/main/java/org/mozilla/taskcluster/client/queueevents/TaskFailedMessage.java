package org.mozilla.taskcluster.client.queueevents;

/**
 * Message reporting that a task failed to complete successfully.
 *
 * See http://schemas.taskcluster.net/queue/v1/task-failed-message.json#
 */
public class TaskFailedMessage {

	/**
	 * Id of the run that failed.
	 */
	public int runId;
	public TaskStatusStructure status;

	/**
	 * Message version
	 */
	public Object version;

	/**
	 * Identifier for the worker-group within which this run ran.
	 */
	public String workerGroup;

	/**
	 * Identifier for the worker that executed this run.
	 */
	public String workerId;
}
