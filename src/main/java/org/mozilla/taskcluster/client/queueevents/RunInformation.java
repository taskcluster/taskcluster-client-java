package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
 * JSON object with information about a run
 *
 * See http://schemas.taskcluster.net/queue/v1/task-status.json#/properties/runs/items
 */
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
     * property is only available after the run is resolved. Some of these
     * reasons, notably `intermittent-task`, `worker-shutdown`, and
     * `claim-expired`, will trigger an automatic retry of the task.
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
