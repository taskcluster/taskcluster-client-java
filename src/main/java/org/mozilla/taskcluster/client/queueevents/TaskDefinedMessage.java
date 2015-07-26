package org.mozilla.taskcluster.client.queueevents;

/**
* Message reporting that a task has been defined. The task may or may not be
* _scheduled_ too.
*
* See http://schemas.taskcluster.net/queue/v1/task-defined-message.json#
*/
public class TaskDefinedMessage {
    public TaskStatusStructure status;

    /**
     * Message version
     */
    public Object version;
}