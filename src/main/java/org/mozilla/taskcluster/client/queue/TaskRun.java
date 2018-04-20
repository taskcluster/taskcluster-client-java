package org.mozilla.taskcluster.client.queue;

/**
 * A run of a task.
 *
 * See http://schemas.taskcluster.net/queue/v1/task-run.json#
 */
public class TaskRun {

    /**
     * Id of this task run, `run-id`s always starts from `0`
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See http://schemas.taskcluster.net/queue/v1/task-run.json#/properties/runId
     */
    public int runId;

    /**
     * Unique task identifier, this is UUID encoded as
     * [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and
     * stripped of `=` padding.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/queue/v1/task-run.json#/properties/taskId
     */
    public String taskId;
}
