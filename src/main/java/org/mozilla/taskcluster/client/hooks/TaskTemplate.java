package org.mozilla.taskcluster.client.hooks;

/**
 * Definition of a task embedded in a hook
 *
 * See http://schemas.taskcluster.net/hooks/v1/task-template.json#
 */
public class TaskTemplate {

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
     *
     * Default:    map[]
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/extra
     */
    public Object extra;

    public class Metadata {

        /**
         * Human readable description of the task, please **explain** what the
         * task does. A few lines of documentation is not going to hurt you.
         *
         * Max length: 32768
         *
         * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/metadata/properties/description
         */
        public String description;

        /**
         * Human readable name of task, used to very briefly given an idea about
         * what the task does.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/metadata/properties/name
         */
        public String name;

        /**
         * E-mail of person who caused this task, e.g. the person who did
         * `hg push`. The person we should contact to ask why this task is here.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/metadata/properties/owner
         */
        public String owner;

        /**
         * Link to source of this task, should specify a file, revision and
         * repository. This should be place someone can go an do a git/hg blame
         * to who came up with recipe for this task.
         *
         * Max length: 4096
         *
         * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/metadata/properties/source
         */
        public String source;
    }

    /**
     * Required task metadata
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/metadata
     */
    public Metadata metadata;

    /**
     * Task-specific payload following worker-specific format. For example the
     * `docker-worker` requires keys like: `image`, `commands` and
     * `features`. Refer to the documentation of `docker-worker` for details.
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/payload
     */
    public Object payload;

    /**
     * Priority of task, this defaults to `normal`. Additional levels may be
     * added later.
     * **Task submitter required scopes** `queue:task-priority:high` for high
     * priority tasks.
     *
     * Possible values:
     *     * "high"
     *     * "normal"
     * Default:    "normal"
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/priority
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
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * Number of times to retry the task in case of infrastructure issues.
     * An _infrastructure issue_ is a worker node that crashes or is shutdown,
     * these events are to be expected.
     *
     * Default:    5
     * Mininum:    0
     * Maximum:    49
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/retries
     */
    public int retries;

    /**
     * List of task specific routes, AMQP messages will be CC'ed to these routes.
     * **Task submitter required scopes** `queue:route:<route>` for
     * each route given.
     *
     * Default:    []
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/routes
     */
    public String[] routes;

    /**
     * Identifier for the scheduler that _defined_ this task, this can be an
     * identifier for a user or a service like the `"task-graph-scheduler"`.
     * **Task submitter required scopes**
     * `queue:assume:scheduler-id:<schedulerId>/<taskGroupId>`.
     * This scope is also necessary to _schedule_ a defined task, or _rerun_ a
     * task.
     *
     * Default:    "-"
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/schedulerId
     */
    public String schedulerId;

    /**
     * List of scopes (or scope-patterns) that the task is
     * authorized to use.
     *
     * Default:    []
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/scopes
     */
    public String[] scopes;

    /**
     * Arbitrary key-value tags (only strings limited to 4k). These can be used
     * to attach informal meta-data to a task. Use this for informal tags that
     * tasks can be classified by. You can also think of strings here as
     * candidates for formal meta-data. Something like
     * `purpose: 'build' || 'test'` is a good example.
     *
     * Default:    map[]
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/tags
     */
    public Object tags;

    /**
     * Identifier for a group of tasks scheduled together with this task, by
     * scheduler identified by `schedulerId`. For tasks scheduled by the
     * task-graph scheduler, this is the `taskGraphId`.  Defaults to `taskId` if
     * property isn't specified.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/taskGroupId
     */
    public String taskGroupId;

    /**
     * Unique identifier for a worker-type within a specific provisioner
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/hooks/v1/task-template.json#/properties/workerType
     */
    public String workerType;
}
