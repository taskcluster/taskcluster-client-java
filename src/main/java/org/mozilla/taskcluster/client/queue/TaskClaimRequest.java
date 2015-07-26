package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* Request to claim (or reclaim) a task
*
* See http://schemas.taskcluster.net/queue/v1/task-claim-request.json#
*/
public class TaskClaimRequest {

    /**
     * Identifier for group that worker claiming the task is a part of.
     */
    public String workerGroup;

    /**
     * Identifier for worker within the given workerGroup
     */
    public String workerId;
}