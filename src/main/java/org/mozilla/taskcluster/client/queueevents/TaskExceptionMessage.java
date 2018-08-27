package org.mozilla.taskcluster.client.queueevents;

/**
 * Message reporting that Taskcluster have failed to run a task.
 *
 * See https://schemas.taskcluster.net/queue/v1/task-exception-message.json#
 */
public class TaskExceptionMessage {

    /**
     * Id of the last run for the task, not provided if `deadline`
     * was exceeded before a run was started.
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See https://schemas.taskcluster.net/queue/v1/task-exception-message.json#/properties/runId
     */
    public int runId;

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-exception-message.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/queue/v1/task-exception-message.json#/properties/version
     */
    public int version;

    /**
     * Identifier for the worker-group within which the last attempt of the task
     * ran. Not provided, if `deadline` was exceeded before a run was started.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task-exception-message.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for the last worker that failed to report, causing the task
     * to fail. Not provided, if `deadline` was exceeded before a run
     * was started.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task-exception-message.json#/properties/workerId
     */
    public String workerId;
}
