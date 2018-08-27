package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
 * Message reporting that a given run of a task have started
 *
 * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#
 */
public class TaskRunningMessage {

    /**
     * Id of the run that just started, always starts from 0
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#/properties/runId
     */
    public int runId;

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Time at which the run expires and is resolved as `failed`, if the run
     * isn't reclaimed.
     *
     * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#/properties/takenUntil
     */
    public Date takenUntil;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#/properties/version
     */
    public int version;

    /**
     * Identifier for the worker-group within which this run started.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for the worker executing this run.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task-running-message.json#/properties/workerId
     */
    public String workerId;
}
