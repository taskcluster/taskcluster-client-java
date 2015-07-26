package org.mozilla.taskcluster.client.scheduler;

/**
* Definition of a task-graph that can be scheduled
*
* See http://schemas.taskcluster.net/scheduler/v1/extend-task-graph-request.json#
*/
public class TaskGraphDefinition {

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
        public TaskDefinition task;

        /**
         * Task identifier (`taskId`) for the task when submitted to the queue, also used in `requires` below. This must be formatted as a **slugid** that is a uuid encoded in url-safe base64 following [RFC 4648 sec. 5](http://tools.ietf.org/html/rfc4648#section-5)), but without `==` padding.
         */
        public String taskId;
    }

    public Tasks[] tasks;
}