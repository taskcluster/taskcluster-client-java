package org.mozilla.taskcluster.client.schedulerevents;

/**
* Message that all reruns of a task has failed it is now blocking the task-graph from finishing.
*
* See http://schemas.taskcluster.net/scheduler/v1/task-graph-blocked-message.json#
*/
public class BlockedTaskGraphMessage {
    public TaskGraphStatusStructure status;

    /**
     * Unique `taskId` that is blocking this task-graph from completion.
     */
    public String taskId;

    /**
     * Message version
     */
    public int version;
}