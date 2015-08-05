package org.mozilla.taskcluster.client.schedulerevents;

/**
* Messages as posted to `scheduler/v1/task-graph-running` informing the world that a new task-graph have been submitted.
*
* See http://schemas.taskcluster.net/scheduler/v1/task-graph-running-message.json#
*/
public class NewTaskGraphMessage {
    public TaskGraphStatusStructure status;

    /**
     * Message version
     */
    public Object version;
}