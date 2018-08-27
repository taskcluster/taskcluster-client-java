package org.mozilla.taskcluster.client.queue;

/**
 * Response to a request for the number of pending tasks for a given
 * `provisionerId` and `workerType`.
 *
 * See https://schemas.taskcluster.net/queue/v1/pending-tasks-response.json#
 */
public class CountPendingTasksResponse {

    /**
     * An approximate number of pending tasks for the given `provisionerId` and
     * `workerType`. This is based on Azure Queue Storage metadata API, thus,
     * number of reported here may be higher than actual number of pending tasks.
     * But there cannot be more pending tasks reported here. Ie. this is an
     * **upper-bound** on the number of pending tasks.
     *
     * Mininum:    0
     *
     * See https://schemas.taskcluster.net/queue/v1/pending-tasks-response.json#/properties/pendingTasks
     */
    public int pendingTasks;

    /**
     * Unique identifier for the provisioner
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/pending-tasks-response.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * Identifier for worker type within the specified provisioner
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/pending-tasks-response.json#/properties/workerType
     */
    public String workerType;
}
