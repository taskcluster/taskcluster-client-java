package org.mozilla.taskcluster.client.queueevents;

/**
 * Message reporting that a task has been defined. The task may or may not be
 * _scheduled_ too.
 *
 * See https://schemas.taskcluster.net/queue/v1/task-defined-message.json#
 */
public class TaskDefinedMessage {

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-defined-message.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/queue/v1/task-defined-message.json#/properties/version
     */
    public int version;
}
