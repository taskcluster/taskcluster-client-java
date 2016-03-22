package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* Definition of a task that can be scheduled
*
* See http://schemas.taskcluster.net/queue/v1/task.json#
*/
public class TaskDefinitionResponse {

    /**
     * Creation time of task
     */
    public Date created;

    /**
     * Deadline of the task, `pending` and `running` runs are resolved as **failed** if not resolved by other means before the deadline. Note, deadline cannot be more than5 days into the future
     */
    public Date deadline;

    /**
     * List of dependent tasks. These must either be _completed_ or _resolved_
     * before this task is scheduled. See `requires` for semantics.
     */
    public String[] dependencies;

    /**
     * Task expiration, time at which task definition and status is deleted.
     * Notice that all artifacts for the must have an expiration that is no
     * later than this. If this property isn't it will be set to `deadline`
     * plus one year (this default may subject to change).
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
     * reusable services. **Warning**, do not stuff large data-sets in here,
     * task definitions should not take-up multiple MiBs.
     */
    public Object extra;

    /**
     * Required task metadata
     */
    public class Metadata {

        /**
         * Human readable description of the task, please **explain** what the
         * task does. A few lines of documentation is not going to hurt you.
         */
        public String description;

        /**
         * Human readable name of task, used to very briefly given an idea about
         * what the task does.
         */
        public String name;

        /**
         * E-mail of person who caused this task, e.g. the person who did
         * `hg push`. The person we should contact to ask why this task is here.
         */
        public String owner;

        /**
         * Link to source of this task, should specify a file, revision and
         * repository. This should be place someone can go an do a git/hg blame
         * to who came up with recipe for this task.
         */
        public String source;
    }

    public Metadata metadata;

    /**
     * Task-specific payload following worker-specific format. For example the
     * `docker-worker` requires keys like: `image`, `commands` and
     * `features`. Refer to the documentation of `docker-worker` for details.
     */
    public Object payload;

    /**
     * Priority of task, this defaults to `normal` and the scope
     * `queue:task-priority:high` is required to define a task with `priority`
     * set to `high`. Additional priority levels may be added later.
     */
    public String priority;

    /**
     * Unique identifier for a provisioner, that can supply specified
     * `workerType`
     */
    public String provisionerId;

    /**
     * The tasks relation to its dependencies. This property specifies the
     * semantics of the `task.dependencies` property.
     * If `all-completed` is given the task will be scheduled when all
     * dependencies are resolved _completed_ (successful resolution).
     * If `all-resolved` is given the task will be scheduled when all dependencies
     * have been resolved, regardless of what their resolution is.
     */
    public String requires;

    /**
     * Number of times to retry the task in case of infrastructure issues.
     * An _infrastructure issue_ is a worker node that crashes or is shutdown,
     * these events are to be expected.
     */
    public int retries;

    /**
     * List of task specific routes, AMQP messages will be CC'ed to these routes.
     */
    public String[] routes;

    /**
     * Identifier for the scheduler that _defined_ this task, this can be an
     * identifier for a user or a service like the `"task-graph-scheduler"`.
     * Along with the `taskGroupId` this is used to form the permission scope
     * `queue:assume:scheduler-id:<schedulerId>/<taskGroupId>`,
     * this scope is necessary to _schedule_ a defined task, or _rerun_ a task.
     */
    public String schedulerId;

    /**
     * List of scopes (or scope-patterns) that the task is
     * authorized to use.
     */
    public String[] scopes;

    /**
     * Arbitrary key-value tags (only strings limited to 4k). These can be used
     * to attach informal meta-data to a task. Use this for informal tags that
     * tasks can be classified by. You can also think of strings here as
     * candidates for formal meta-data. Something like
     * `purpose: 'build' || 'test'` is a good example.
     */
    public Object tags;

    /**
     * Identifier for a group of tasks scheduled together with this task, by
     * scheduler identified by `schedulerId`. For tasks scheduled by the
     * task-graph scheduler, this is the `taskGraphId`.  Defaults to `taskId` if
     * property isn't specified.
     */
    public String taskGroupId;

    /**
     * Unique identifier for a worker-type within a specific provisioner
     */
    public String workerType;
}