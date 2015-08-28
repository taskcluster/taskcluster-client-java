package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* Response to a successful task claim
*
* See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#
*/
public class TaskClaimResponse {

    /**
     * `run-id` assigned to this run of the task
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Time at which the run expires and is resolved as `failed`,
     * if the run isn't reclaimed.
     */
    public Date takenUntil;

    /**
     * Identifier for the worker-group within which this run started.
     */
    public String workerGroup;

    /**
     * Identifier for the worker executing this run.
     */
    public String workerId;
}