package org.mozilla.taskcluster.client.scheduler;

/**
 * Definition of a task-graph that can be scheduled
 *
 * See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#
 */
public class TaskGraphDefinition1 {

    public class TaskNode {

        /**
         * List of required `taskId`s
         *
         * Default:    []
         *
         * See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#/properties/tasks/items/properties/requires
         */
        public String[] requires;

        /**
         * Number of times to _rerun_ the task if it completed unsuccessfully. **Note**, this does not capture _retries_ due to infrastructure issues.
         *
         * Default:    0
         * Mininum:    0
         * Maximum:    100
         *
         * See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#/properties/tasks/items/properties/reruns
         */
        public int reruns;

        /**
         * See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#/properties/tasks/items/properties/task
         */
        public TaskDefinitionRequest task;

        /**
         * Task identifier (`taskId`) for the task when submitted to the queue, also used in `requires` below. This must be formatted as a **slugid** that is a uuid encoded in url-safe base64 following [RFC 4648 sec. 5](http://tools.ietf.org/html/rfc4648#section-5)), but without `==` padding.
         *
         * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
         *
         * See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#/properties/tasks/items/properties/taskId
         */
        public String taskId;
    }

    /**
     * List of nodes in the task-graph, each featuring a task definition and scheduling preferences, such as number of _reruns_ to attempt.
     *
     * See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#/properties/tasks
     */
    public TaskNode[] tasks;
}
