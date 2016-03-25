package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
* A representation of **task status** as known by the queue
*
* See http://schemas.taskcluster.net/queue/v1/task-status.json#
*/
public class TaskStatusStructure {

    /**
     * Deadline of the task, `pending` and `running` runs are
     * resolved as **failed** if not resolved by other means
     * before the deadline. Note, deadline cannot be more than
     * 5 days into the future
     */
    public Date deadline;

    /**
     * Task expiration, time at which task definition and
     * status is deleted. Notice that all artifacts for the
     * must have an expiration that is no later than this.
     */
    public Date expires;

    /**
     * Unique identifier for the provisioner that this task must be scheduled on
     */
    public String provisionerId;

    /**
     * Number of retries left for the task in case of infrastructure issues
     */
    public int retriesLeft;

    /**
     * List of runs, ordered so that index `i` has `runId == i`
     */
    public class Runs {

        /**
         * Reason for the creation of this run,
         * **more reasons may be added in the future**.
         */
        public String reasonCreated;

        /**
         * Reason that run was resolved, this is mainly
         * useful for runs resolved as `exception`.
         * Note, **more reasons may be added in the future**, also this
         * property is only available after the run is resolved.
         */
        public String reasonResolved;

        /**
         * Date-time at which this run was resolved, ie. when the run changed
         * state from `running` to either `completed`, `failed` or `exception`.
         * This property is only present after the run as been resolved.
         */
        public Date resolved;

        /**
         * Id of this task run, `run-id`s always starts from `0`
         */
        public int runId;

        /**
         * Date-time at which this run was scheduled, ie. when the run was
         * created in state `pending`.
         */
        public Date scheduled;

        /**
         * Date-time at which this run was claimed, ie. when the run changed
         * state from `pending` to `running`. This property is only present
         * after the run has been claimed.
         */
        public Date started;

        /**
         * State of this run
         */
        public String state;

        /**
         * Time at which the run expires and is resolved as `failed`, if the
         * run isn't reclaimed. Note, only present after the run has been
         * claimed.
         */
        public Date takenUntil;

        /**
         * Identifier for group that worker who executes this run is a part of,
         * this identifier is mainly used for efficient routing.
         * Note, this property is only present after the run is claimed.
         */
        public String workerGroup;

        /**
         * Identifier for worker evaluating this run within given
         * `workerGroup`. Note, this property is only available after the run
         * has been claimed.
         */
        public String workerId;
    }

    public Runs[] runs;

    /**
     * Identifier for the scheduler that _defined_ this task.
     */
    public String schedulerId;

    /**
     * State of this task. This is just an auxiliary property derived from state
     * of latests run, or `unscheduled` if none.
     */
    public String state;

    /**
     * Identifier for a group of tasks scheduled together with this task, by
     * scheduler identified by `schedulerId`. For tasks scheduled by the
     * task-graph scheduler, this is the `taskGraphId`.
     */
    public String taskGroupId;

    /**
     * Unique task identifier, this is UUID encoded as
     * [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and
     * stripped of `=` padding.
     */
    public String taskId;

    /**
     * Identifier for worker type within the specified provisioner
     */
    public String workerType;
}