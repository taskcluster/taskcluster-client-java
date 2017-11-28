package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response containing information about a worker.
 *
 * See http://schemas.taskcluster.net/queue/v1/worker-response.json#
 */
public class WorkerResponse {

    public class Actions1 {

        /**
         * Actions have a "context" that is one of provisioner, worker-type,
         * or worker, indicating which it applies to. `context` is used by the front-end to know where to display the action.
         *
         * | `context`   | Page displayed        |
         * |-------------|-----------------------|
         * | provisioner | Provisioner Explorer  |
         * | worker-type | Workers Explorer      |
         * | worker      | Worker Explorer       |
         *
         * Possible values:
         *     * "worker"
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/context
         */
        public String context;

        /**
         * Description of the provisioner.
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/description
         */
        public String description;

        /**
         * Method to indicate the desired action to be performed for a given resource.
         *
         * Possible values:
         *     * "POST"
         *     * "PUT"
         *     * "DELETE"
         *     * "PATCH"
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/method
         */
        public String method;

        /**
         * Short names for things like logging/error messages.
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/name
         */
        public String name;

        /**
         * Appropriate title for any sort of Modal prompt.
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/title
         */
        public Object title;

        /**
         * When an action is triggered, a request is made using the `url` and `method`.
         * Depending on the `context`, the following parameters will be substituted in the url:
         *
         * | `context`   | Path parameters                                          |
         * |-------------|----------------------------------------------------------|
         * | provisioner | <provisionerId>                                          |
         * | worker-type | <provisionerId>, <workerType>                            |
         * | worker      | <provisionerId>, <workerType>, <workerGroup>, <workerId> |
         *
         * _Note: The request needs to be signed with the user's Taskcluster credentials._
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/url
         */
        public String url;
    }

    /**
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions
     */
    public Actions1[] actions;

    /**
     * Date and time after which the worker will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Date of the first time this worker claimed a task.
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/firstClaim
     */
    public Date firstClaim;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * Quarantining a worker allows the machine to remain alive but not accept jobs.
     * Once the quarantineUntil time has elapsed, the worker resumes accepting jobs.
     * Note that a quarantine can be lifted by setting `quarantineUntil` to the present time (or
     * somewhere in the past).
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/quarantineUntil
     */
    public Date quarantineUntil;

    public class RecentTasksEntry {

        /**
         * Id of this task run, `run-id`s always starts from `0`
         *
         * Mininum:    0
         * Maximum:    1000
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/recentTasks/items/properties/runId
         */
        public int runId;

        /**
         * Unique task identifier, this is UUID encoded as
         * [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and
         * stripped of `=` padding.
         *
         * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
         *
         * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/recentTasks/items/properties/taskId
         */
        public String taskId;
    }

    /**
     * List of 20 most recent tasks claimed by the worker.
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/recentTasks
     */
    public RecentTasksEntry[] recentTasks;

    /**
     * Identifier for group that worker who executes this run is a part of,
     * this identifier is mainly used for efficient routing.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for worker evaluating this run within given
     * `workerGroup`.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/workerId
     */
    public String workerId;

    /**
     * WorkerType name.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/workerType
     */
    public String workerType;
}
