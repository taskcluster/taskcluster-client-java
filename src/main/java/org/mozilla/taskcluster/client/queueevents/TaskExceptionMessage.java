package org.mozilla.taskcluster.client.queueevents;

/**
 * Message reporting that TaskCluster have failed to run a task.
 *
 * See http://schemas.taskcluster.net/queue/v1/task-exception-message.json#
 */
public class TaskExceptionMessage {

	/**
	 * Id of the last run for the task, not provided if `deadline` was exceeded
	 * before a run was started.
	 */
	public int runId;
	public TaskStatusStructure status;

	/**
	 * Message version
	 */
	public Object version;

	/**
	 * Identifier for the worker-group within which the last attempt of the task
	 * ran. Not provided, if `deadline` was exceeded before a run was started.
	 */
	public String workerGroup;

	/**
	 * Identifier for the last worker that failed to report, causing the task to
	 * fail. Not provided, if `deadline` was exceeded before a run was started.
	 */
	public String workerId;
}
