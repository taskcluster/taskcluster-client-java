package org.mozilla.taskcluster.client.queueevents;

/**
* Message reporting that a task is now pending
*
* See http://schemas.taskcluster.net/queue/v1/task-pending-message.json#
*/
public class TaskPendingMessage {

    /**
     * Id of run that became pending, `run-id`s always starts from 0
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Message version
     */
    public Object version;
}