package org.mozilla.taskcluster.client.queue;

/**
 * Request to claim a task for a worker to process.
 *
 * See http://schemas.taskcluster.net/queue/v1/claim-work-request.json#
 */
public class ClaimWorkRequest {

    /**
     * Number of tasks to attempt to claim.
     *
     * Default:    1
     * Mininum:    1
     * Maximum:    32
     *
     * See http://schemas.taskcluster.net/queue/v1/claim-work-request.json#/properties/tasks
     */
    public int tasks;

    /**
     * Identifier for group that worker claiming the task is a part of.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/claim-work-request.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for worker within the given workerGroup
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/claim-work-request.json#/properties/workerId
     */
    public String workerId;
}
