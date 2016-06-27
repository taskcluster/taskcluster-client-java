package org.mozilla.taskcluster.client.schedulerevents;

/**
 * Message that all reruns of a task has failed it is now blocking the task-graph from finishing.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-blocked-message.json#
 */
public class BlockedTaskGraphMessage {

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-blocked-message.json#/properties/status
     */
    public TaskGraphStatusStructure status;

    /**
     * Unique `taskId` that is blocking this task-graph from completion.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-blocked-message.json#/properties/taskId
     */
    public String taskId;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-blocked-message.json#/properties/version
     */
    public int version;
}
