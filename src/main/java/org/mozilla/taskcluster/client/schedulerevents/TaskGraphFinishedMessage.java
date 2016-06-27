package org.mozilla.taskcluster.client.schedulerevents;

/**
 * Message that all tasks in a task-graph have now completed successfully and the graph is _finished_.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-finished-message.json#
 */
public class TaskGraphFinishedMessage {

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-finished-message.json#/properties/status
     */
    public TaskGraphStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-finished-message.json#/properties/version
     */
    public int version;
}
