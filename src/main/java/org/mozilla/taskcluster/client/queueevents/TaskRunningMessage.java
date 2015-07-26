package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
* Message reporting that a given run of a task have started
*
* See http://schemas.taskcluster.net/queue/v1/task-running-message.json#
*/
public class TaskRunningMessage {

    /**
     * Id of the run that just started, always starts from 0
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Time at which the run expires and is resolved as `failed`, if the run
     * isn't reclaimed.
     */
    public Date takenUntil;

    /**
     * Message version
     */
    public Object version;

    /**
     * Identifier for the worker-group within which this run started.
     */
    public String workerGroup;

    /**
     * Identifier for the worker executing this run.
     */
    public String workerId;
}