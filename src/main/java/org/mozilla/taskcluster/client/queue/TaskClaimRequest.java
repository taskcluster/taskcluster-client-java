package org.mozilla.taskcluster.client.queue;

/**
 * Request to claim (or reclaim) a task
 *
 * See http://schemas.taskcluster.net/queue/v1/task-claim-request.json#
 */
public class TaskClaimRequest {

    /**
     * Identifier for group that worker claiming the task is a part of.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-request.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for worker within the given workerGroup
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-claim-request.json#/properties/workerId
     */
    public String workerId;
}
