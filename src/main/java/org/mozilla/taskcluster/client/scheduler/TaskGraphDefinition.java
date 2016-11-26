package org.mozilla.taskcluster.client.scheduler;

/**
 * Definition of a task-graph that can be scheduled
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#
 */
public class TaskGraphDefinition {

    public class MetaData {

        /**
         * Human readable description of task-graph, **explain** what it does!
         *
         * Max length: 32768
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/metadata/properties/description
         */
        public String description;

        /**
         * Human readable name of task-graph, give people finding this an idea
         * what this graph is about.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/metadata/properties/name
         */
        public String name;

        /**
         * E-mail of person who caused this task-graph, e.g. the person who did
         * `hg push` or whatever triggered it.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/metadata/properties/owner
         */
        public String owner;

        /**
         * Link to source of this task-graph, should specify file, revision and
         * repository
         *
         * Max length: 4096
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/metadata/properties/source
         */
        public String source;
    }

    /**
     * Required task metadata
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/metadata
     */
    public MetaData metadata;

    /**
     * List of task-graph specific routes, AMQP messages will be CC'ed to these
     * routes prefixed by `'route.'`.
     *
     * Default:    []
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/routes
     */
    public String[] routes;

    /**
     * List of scopes (or scope-patterns) that tasks of the task-graph is
     * authorized to use.
     *
     * Default:    []
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/scopes
     */
    public String[] scopes;

    /**
     * Arbitrary key-value tags (only strings limited to 4k)
     *
     * Default:    map[]
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/tags
     */
    public Object tags;

    public class TaskNode {

        /**
         * List of required `taskId`s
         *
         * Default:    []
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/tasks/items/properties/requires
         */
        public String[] requires;

        /**
         * Number of times to _rerun_ the task if it completed unsuccessfully. **Note**, this does not capture _retries_ due to infrastructure issues.
         *
         * Default:    0
         * Mininum:    0
         * Maximum:    100
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/tasks/items/properties/reruns
         */
        public int reruns;

        /**
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/tasks/items/properties/task
         */
        public TaskDefinitionRequest task;

        /**
         * Task identifier (`taskId`) for the task when submitted to the queue, also used in `requires` below. This must be formatted as a **slugid** that is a uuid encoded in url-safe base64 following [RFC 4648 sec. 5](http://tools.ietf.org/html/rfc4648#section-5)), but without `==` padding.
         *
         * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/tasks/items/properties/taskId
         */
        public String taskId;
    }

    /**
     * List of nodes in the task-graph, each featuring a task definition and scheduling preferences, such as number of _reruns_ to attempt.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#/properties/tasks
     */
    public TaskNode[] tasks;
}
