package org.mozilla.taskcluster.client.schedulerevents;

/**
 * Messages as posted to `scheduler/v1/task-graph-running` informing the world that a new task-graph have been submitted.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-running-message.json#
 */
public class NewTaskGraphMessage {

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-running-message.json#/properties/status
     */
    public TaskGraphStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-running-message.json#/properties/version
     */
    public int version;
}
