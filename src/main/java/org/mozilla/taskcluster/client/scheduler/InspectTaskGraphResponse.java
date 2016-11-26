package org.mozilla.taskcluster.client.scheduler;

/**
 * Information about a **task-graph** as known by the scheduler, with all the state of all individual tasks.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#
 */
public class InspectTaskGraphResponse {

    public class MetaData {

        /**
         * Human readable description of task-graph, **explain** what it does!
         *
         * Max length: 32768
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/metadata/properties/description
         */
        public String description;

        /**
         * Human readable name of task-graph
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/metadata/properties/name
         */
        public String name;

        /**
         * E-mail of person who caused this task-graph, e.g. the person who did `hg push`
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/metadata/properties/owner
         */
        public String owner;

        /**
         * Link to source of this task-graph, should specify file, revision and repository
         *
         * Max length: 4096
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/metadata/properties/source
         */
        public String source;
    }

    /**
     * Required task metadata
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/metadata
     */
    public MetaData metadata;

    /**
     * List of scopes (or scope-patterns) that tasks of the task-graph is authorized to use.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/scopes
     */
    public String[] scopes;

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/status
     */
    public TaskGraphStatusStructure status;

    /**
     * Arbitrary key-value tags (only strings limited to 4k)
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tags
     */
    public Object tags;

    public class TaskInformation {

        /**
         * List of `taskId`s that requires this task to be _complete successfully_ before they can be scheduled.
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/dependents
         */
        public String[] dependents;

        /**
         * Human readable name from the task definition
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/name
         */
        public String name;

        /**
         * List of required `taskId`s
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/requires
         */
        public String[] requires;

        /**
         * List of `taskId`s that have yet to complete successfully, before this task can be scheduled.
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/requiresLeft
         */
        public String[] requiresLeft;

        /**
         * Number of times to _rerun_ the task if it completed unsuccessfully. **Note**, this does not capture _retries_ due to infrastructure issues.
         *
         * Mininum:    0
         * Maximum:    999
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/reruns
         */
        public int reruns;

        /**
         * Number of reruns that haven't been used yet.
         *
         * Mininum:    0
         * Maximum:    999
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/rerunsLeft
         */
        public int rerunsLeft;

        /**
         * true, if the scheduler considers the task node as satisfied and hence no-longer prevents dependent tasks from running.
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/satisfied
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
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/state
         */
        public String state;

        /**
         * Unique task identifier, this is UUID encoded as [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and stripped of `=` padding.
         *
         * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
         *
         * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks/items/properties/taskId
         */
        public String taskId;
    }

    /**
     * Mapping from task-labels to task information and state.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/inspect-task-graph-response.json#/properties/tasks
     */
    public TaskInformation[] tasks;
}
