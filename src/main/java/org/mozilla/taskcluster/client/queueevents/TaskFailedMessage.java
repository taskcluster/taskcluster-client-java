package org.mozilla.taskcluster.client.queueevents;

/**
 * Message reporting that a task failed to complete successfully.
 *
 * See https://schemas.taskcluster.net/queue/v1/task-failed-message.json#
 */
public class TaskFailedMessage {

    /**
     * Id of the run that failed.
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See https://schemas.taskcluster.net/queue/v1/task-failed-message.json#/properties/runId
     */
    public int runId;

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-failed-message.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/queue/v1/task-failed-message.json#/properties/version
     */
    public int version;

    /**
     * Identifier for the worker-group within which this run ran.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task-failed-message.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for the worker that executed this run.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task-failed-message.json#/properties/workerId
     */
    public String workerId;
}
