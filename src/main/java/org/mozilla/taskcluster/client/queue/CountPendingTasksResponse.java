package org.mozilla.taskcluster.client.queue;

/**
 * Response to a request for the number of pending tasks for a given
 * `provisionerId` and `workerType`.
 *
 * See http://schemas.taskcluster.net/queue/v1/pending-tasks-response.json#
 */
public class CountPendingTasksResponse {

	/**
	 * An approximate number of pending tasks for the given `provisionerId` and
	 * `workerType`. This is based on Azure Queue Storage meta-data API, thus,
	 * number of reported here may be higher than actual number of pending
	 * tasks. But there cannot be more pending tasks reported here. Ie. this is
	 * an **upper-bound** on the number of pending tasks.
	 */
	public int pendingTasks;

	/**
	 * Unique identifier for the provisioner
	 */
	public String provisionerId;

	/**
	 * Identifier for worker type within the specified provisioner
	 */
	public String workerType;
}
