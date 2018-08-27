package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items
 */
public class TaskClaim {

    /**
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/credentials
     */
    public TaskCredentials credentials;

    /**
     * `run-id` assigned to this run of the task
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/runId
     */
    public int runId;

    /**
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Time at which the run expires and is resolved as `exception`,
     * with reason `claim-expired` if the run haven't been reclaimed.
     *
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/takenUntil
     */
    public Date takenUntil;

    /**
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/task
     */
    public TaskDefinitionResponse task;

    /**
     * Identifier for the worker-group within which this run started.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for the worker executing this run.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/workerId
     */
    public String workerId;
}
