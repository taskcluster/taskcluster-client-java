package org.mozilla.taskcluster.client.scheduler;

/**
 * Information about a **task** in a task-graph as known by the scheduler.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#
 */
public class InspectTaskGraphTaskResponse {

    /**
     * List of `taskId`s that requires this task to be _complete successfully_ before they can be scheduled.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/dependents
     */
    public String[] dependents;

    /**
     * Human readable name from the task definition
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/name
     */
    public String name;

    /**
     * List of required `taskId`s
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/requires
     */
    public String[] requires;

    /**
     * List of `taskId`s that have yet to complete successfully, before this task can be scheduled.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/requiresLeft
     */
    public String[] requiresLeft;

    /**
     * Number of times to _rerun_ the task if it completed unsuccessfully. **Note**, this does not capture _retries_ due to infrastructure issues.
     *
     * Mininum:    0
     * Maximum:    999
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/reruns
     */
    public int reruns;

    /**
     * Number of reruns that haven't been used yet.
     *
     * Mininum:    0
     * Maximum:    999
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/rerunsLeft
     */
    public int rerunsLeft;

    /**
     * true, if the scheduler considers the task node as satisfied and hence no-longer prevents dependent tasks from running.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/satisfied
     */
    public boolean satisfied;

    /**
     * State of the task as considered by the scheduler
     *
     * Possible values:
     *     * "unscheduled"
     *     * "scheduled"
     *     * "completed"
     *     * "failed"
     *     * "exception"
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/state
     */
    public String state;

    /**
     * Unique task identifier, this is UUID encoded as [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and stripped of `=` padding.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-task-response.json#/properties/taskId
     */
    public String taskId;
}
