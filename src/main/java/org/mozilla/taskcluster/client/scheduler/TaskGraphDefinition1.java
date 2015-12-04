package org.mozilla.taskcluster.client.scheduler;

/**
* Definition of a task-graph that can be scheduled
*
* See http://schemas.taskcluster.net/scheduler/v1/task-graph.json#
*/
public class TaskGraphDefinition1 {

    /**
     * Required task metadata
     */
    public class Metadata {

        /**
         * Human readable description of task-graph, **explain** what it does!
         */
        public String description;

        /**
         * Human readable name of task-graph, give people finding this an idea
         * what this graph is about.
         */
        public String name;

        /**
         * E-mail of person who caused this task-graph, e.g. the person who did
         * `hg push` or whatever triggered it.
         */
        public String owner;

        /**
         * Link to source of this task-graph, should specify file, revision and
         * repository
         */
        public String source;
    }

    public Metadata metadata;

    /**
     * List of task-graph specific routes, AMQP messages will be CC'ed to these
     * routes prefixed by `'route.'`.
     */
    public String[] routes;

    /**
     * List of scopes (or scope-patterns) that tasks of the task-graph is
     * authorized to use.
     */
    public String[] scopes;

    /**
     * Arbitrary key-value tags (only strings limited to 4k)
     */
    public Object tags;

    /**
     * List of nodes in the task-graph, each featuring a task definition and scheduling preferences, such as number of _reruns_ to attempt.
     */
    public class Tasks {

        /**
         * List of required `taskId`s
         */
        public String[] requires;

        /**
         * Number of times to _rerun_ the task if it completed unsuccessfully. **Note**, this does not capture _retries_ due to infrastructure issues.
         */
        public int reruns;
        public TaskDefinitionRequest task;

        /**
         * Task identifier (`taskId`) for the task when submitted to the queue, also used in `requires` below. This must be formatted as a **slugid** that is a uuid encoded in url-safe base64 following [RFC 4648 sec. 5](http://tools.ietf.org/html/rfc4648#section-5)), but without `==` padding.
         */
        public String taskId;
    }

    public Tasks[] tasks;
}