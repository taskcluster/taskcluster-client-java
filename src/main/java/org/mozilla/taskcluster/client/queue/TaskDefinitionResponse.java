package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Definition of a task that can be scheduled
 *
 * See https://schemas.taskcluster.net/queue/v1/task.json#
 */
public class TaskDefinitionResponse {

    /**
     * Creation time of task
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/created
     */
    public Date created;

    /**
     * Deadline of the task, `pending` and `running` runs are
     * resolved as **exception** if not resolved by other means
     * before the deadline. Note, deadline cannot be more than
     * 5 days into the future
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/deadline
     */
    public Date deadline;

    /**
     * List of dependent tasks. These must either be _completed_ or _resolved_
     * before this task is scheduled. See `requires` for semantics.
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/dependencies
     */
    public String[] dependencies;

    /**
     * Task expiration, time at which task definition and status is deleted.
     * Notice that all artifacts for the task must have an expiration that is no
     * later than this. If this property isn't it will be set to `deadline`
     * plus one year (this default may change).
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/expires
     */
    public Date expires;

    /**
     * Object with properties that can hold any kind of extra data that should be
     * associated with the task. This can be data for the task which doesn't
     * fit into `payload`, or it can supplementary data for use in services
     * listening for events from this task. For example this could be details to
     * display on _treeherder_, or information for indexing the task. Please, try
     * to put all related information under one property, so `extra` data keys
     * for treeherder reporting and task indexing don't conflict, hence, we have
     * reusable services. **Warning**, do not stuff large data-sets in here --
     * task definitions should not take-up multiple MiBs.
     *
     * Default:    {}
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/extra
     */
    public Object extra;

    /**
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/metadata
     */
    public TaskMetadata metadata;

    /**
     * Task-specific payload following worker-specific format.
     * Refer to the documentation for the worker implementing
     * `<provisionerId>/<workerType>` for details.
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/payload
     */
    public Object payload;

    /**
     * Priority of task. This defaults to `lowest` and the scope
     * `queue:create-task:<priority>/<provisionerId>/<workerType>` is required
     * to define a task with `<priority>`. The `normal` priority is treated as
     * `lowest`.
     *
     * Possible values:
     *     * "highest"
     *     * "very-high"
     *     * "high"
     *     * "medium"
     *     * "low"
     *     * "very-low"
     *     * "lowest"
     *     * "normal"
     * Default:    "lowest"
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/priority
     */
    public String priority;

    /**
     * Unique identifier for a provisioner, that can supply specified
     * `workerType`
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * The tasks relation to its dependencies. This property specifies the
     * semantics of the `task.dependencies` property.
     * If `all-completed` is given the task will be scheduled when all
     * dependencies are resolved _completed_ (successful resolution).
     * If `all-resolved` is given the task will be scheduled when all dependencies
     * have been resolved, regardless of what their resolution is.
     *
     * Possible values:
     *     * "all-completed"
     *     * "all-resolved"
     * Default:    "all-completed"
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/requires
     */
    public String requires;

    /**
     * Number of times to retry the task in case of infrastructure issues.
     * An _infrastructure issue_ is a worker node that crashes or is shutdown,
     * these events are to be expected.
     *
     * Default:    5
     * Mininum:    0
     * Maximum:    49
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/retries
     */
    public int retries;

    /**
     * List of task-specific routes. Pulse messages about the task will be CC'ed to
     * `route.<value>` for each `<value>` in this array.
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/routes
     */
    public String[] routes;

    /**
     * All tasks in a task group must have the same `schedulerId`. This is used for several purposes:
     *
     * * it can represent the entity that created the task;
     * * it can limit addition of new tasks to a task group: the caller of
     *     `createTask` must have a scope related to the `schedulerId` of the task
     *     group;
     * * it controls who can manipulate tasks, again by requiring
     *     `schedulerId`-related scopes; and
     * * it appears in the routing key for Pulse messages about the task.
     *
     * Default:    "-"
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/schedulerId
     */
    public String schedulerId;

    /**
     * List of scopes that the task is authorized to use during its execution.
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/scopes
     */
    public String[] scopes;

    /**
     * Arbitrary key-value tags (only strings limited to 4k). These can be used
     * to attach informal metadata to a task. Use this for informal tags that
     * tasks can be classified by. You can also think of strings here as
     * candidates for formal metadata. Something like
     * `purpose: 'build' || 'test'` is a good example.
     *
     * Default:    {}
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/tags
     */
    public Object tags;

    /**
     * Identifier for a group of tasks scheduled together with this task.
     * Generally, all tasks related to a single event such as a version-control
     * push or a nightly build have the same `taskGroupId`.  This property
     * defaults to `taskId` if it isn't specified.  Tasks with `taskId` equal to
     * the `taskGroupId` are, [by convention](/docs/manual/using/task-graph),
     * decision tasks.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/taskGroupId
     */
    public String taskGroupId;

    /**
     * Unique identifier for a worker-type within a specific provisioner
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/task.json#/properties/workerType
     */
    public String workerType;
}
