package org.mozilla.taskcluster.client.scheduler;

/**
 * A representation of **task-graph status** as known by the scheduler, without the state of all individual tasks.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status.json#
 */
public class TaskGraphStatusStructure {

    /**
     * Unique identifier for task-graph scheduler managing the given task-graph
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status.json#/properties/schedulerId
     */
    public String schedulerId;

    /**
     * Task-graph state, this enum is **frozen** new values will **not** be added.
     *
     * Possible values:
     *     * "running"
     *     * "blocked"
     *     * "finished"
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status.json#/properties/state
     */
    public String state;

    /**
     * Unique task-graph identifier, this is UUID encoded as [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and stripped of `=` padding.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status.json#/properties/taskGraphId
     */
    public String taskGraphId;
}
