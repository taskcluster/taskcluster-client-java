package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response containing information about a worker.
 *
 * See https://schemas.taskcluster.net/queue/v1/worker-response.json#
 */
public class WorkerResponse {

    public class WorkerAction {

        /**
         * Only actions with the context `worker` are included.
         *
         * Possible values:
         *     * "worker"
         *
         * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/context
         */
        public String context;

        /**
         * Description of the provisioner.
         *
         * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/description
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
         * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/method
         */
        public String method;

        /**
         * Short names for things like logging/error messages.
         *
         * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/name
         */
        public String name;

        /**
         * Appropriate title for any sort of Modal prompt.
         *
         * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/title
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
         * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions/items/properties/url
         */
        public String url;
    }

    /**
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/actions
     */
    public WorkerAction[] actions;

    /**
     * Date and time after which the worker will be automatically
     * deleted by the queue.
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Date of the first time this worker claimed a task.
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/firstClaim
     */
    public Date firstClaim;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * Quarantining a worker allows the machine to remain alive but not accept jobs.
     * Once the quarantineUntil time has elapsed, the worker resumes accepting jobs.
     * Note that a quarantine can be lifted by setting `quarantineUntil` to the present time (or
     * somewhere in the past).
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/quarantineUntil
     */
    public Date quarantineUntil;

    /**
     * List of 20 most recent tasks claimed by the worker.
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/recentTasks
     */
    public TaskRun[] recentTasks;

    /**
     * Identifier for group that worker who executes this run is a part of,
     * this identifier is mainly used for efficient routing.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/workerGroup
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
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/workerId
     */
    public String workerId;

    /**
     * WorkerType name.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/workerType
     */
    public String workerType;
}
