package org.mozilla.taskcluster.client.queueevents;

/**
* Message reporting that a task has complete successfully.
*
* See http://schemas.taskcluster.net/queue/v1/task-completed-message.json#
*/
public class TaskCompletedMessage {

    /**
     * Id of the run that completed the task
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Message version
     */
    public int version;

    /**
     * Identifier for the worker-group within which this run ran.
     */
    public String workerGroup;

    /**
     * Identifier for the worker that executed this run.
     */
    public String workerId;
}