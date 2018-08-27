package org.mozilla.taskcluster.client.queueevents;

/**
 * Message reporting that a task is now pending
 *
 * See https://schemas.taskcluster.net/queue/v1/task-pending-message.json#
 */
public class TaskPendingMessage {

    /**
     * Id of run that became pending, `run-id`s always starts from 0
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See https://schemas.taskcluster.net/queue/v1/task-pending-message.json#/properties/runId
     */
    public int runId;

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-pending-message.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/queue/v1/task-pending-message.json#/properties/version
     */
    public int version;
}
