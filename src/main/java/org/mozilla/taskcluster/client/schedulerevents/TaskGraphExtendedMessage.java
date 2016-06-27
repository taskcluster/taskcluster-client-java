package org.mozilla.taskcluster.client.schedulerevents;

/**
 * Messages as posted to `scheduler/v1/task-graph-extended` informing the world that a task-graph have been extended.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-extended-message.json#
 */
public class TaskGraphExtendedMessage {

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-extended-message.json#/properties/status
     */
    public TaskGraphStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-extended-message.json#/properties/version
     */
    public int version;
}
