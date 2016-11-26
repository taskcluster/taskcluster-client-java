package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * A representation of **task status** as known by the queue
 *
 * See http://schemas.taskcluster.net/queue/v1/task-status.json#
 */
public class TaskStatusStructure {

    /**
     * Deadline of the task, `pending` and `running` runs are
     * resolved as **exception** if not resolved by other means
     * before the deadline. Note, deadline cannot be more than
     * 5 days into the future
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/deadline
     */
    public Date deadline;

    /**
     * Task expiration, time at which task definition and
     * status is deleted. Notice that all artifacts for the task
     * must have an expiration that is no later than this.
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/expires
     */
    public Date expires;

    /**
     * Unique identifier for the provisioner that this task must be scheduled on
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * Number of retries left for the task in case of infrastructure issues
     *
     * Mininum:    0
     * Maximum:    999
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/retriesLeft
     */
    public int retriesLeft;

    public class RunInformation {

        /**
         * Reason for the creation of this run,
         * **more reasons may be added in the future**.
         *
         * Possible values:
         *     * "scheduled"
         *     * "retry"
         *     * "task-retry"
         *     * "rerun"
         *     * "exception"
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/reasonCreated
         */
        public String reasonCreated;

        /**
         * Reason that run was resolved, this is mainly
         * useful for runs resolved as `exception`.
         * Note, **more reasons may be added in the future**, also this
         * property is only available after the run is resolved.
         *
         * Possible values:
         *     * "completed"
         *     * "failed"
         *     * "deadline-exceeded"
         *     * "canceled"
         *     * "superseded"
         *     * "claim-expired"
         *     * "worker-shutdown"
         *     * "malformed-payload"
         *     * "resource-unavailable"
         *     * "internal-error"
         *     * "intermittent-task"
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/reasonResolved
         */
        public String reasonResolved;

        /**
         * Date-time at which this run was resolved, ie. when the run changed
         * state from `running` to either `completed`, `failed` or `exception`.
         * This property is only present after the run as been resolved.
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/resolved
         */
        public Date resolved;

        /**
         * Id of this task run, `run-id`s always starts from `0`
         *
         * Mininum:    0
         * Maximum:    1000
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/runId
         */
        public int runId;

        /**
         * Date-time at which this run was scheduled, ie. when the run was
         * created in state `pending`.
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/scheduled
         */
        public Date scheduled;

        /**
         * Date-time at which this run was claimed, ie. when the run changed
         * state from `pending` to `running`. This property is only present
         * after the run has been claimed.
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/started
         */
        public Date started;

        /**
         * State of this run
         *
         * Possible values:
         *     * "pending"
         *     * "running"
         *     * "completed"
         *     * "failed"
         *     * "exception"
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/state
         */
        public String state;

        /**
         * Time at which the run expires and is resolved as `failed`, if the
         * run isn't reclaimed. Note, only present after the run has been
         * claimed.
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/takenUntil
         */
        public Date takenUntil;

        /**
         * Identifier for group that worker who executes this run is a part of,
         * this identifier is mainly used for efficient routing.
         * Note, this property is only present after the run is claimed.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/workerGroup
         */
        public String workerGroup;

        /**
         * Identifier for worker evaluating this run within given
         * `workerGroup`. Note, this property is only available after the run
         * has been claimed.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items/properties/workerId
         */
        public String workerId;
    }

    /**
     * List of runs, ordered so that index `i` has `runId == i`
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs
     */
    public RunInformation[] runs;

    /**
     * Identifier for the scheduler that _defined_ this task.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/schedulerId
     */
    public String schedulerId;

    /**
     * State of this task. This is just an auxiliary property derived from state
     * of latests run, or `unscheduled` if none.
     *
     * Possible values:
     *     * "unscheduled"
     *     * "pending"
     *     * "running"
     *     * "completed"
     *     * "failed"
     *     * "exception"
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/state
     */
    public String state;

    /**
     * Identifier for a group of tasks scheduled together with this task, by
     * scheduler identified by `schedulerId`. For tasks scheduled by the
     * task-graph scheduler, this is the `taskGraphId`.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/taskGroupId
     */
    public String taskGroupId;

    /**
     * Unique task identifier, this is UUID encoded as
     * [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and
     * stripped of `=` padding.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/taskId
     */
    public String taskId;

    /**
     * Identifier for worker type within the specified provisioner
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/workerType
     */
    public String workerType;
}
