package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response to a worker-type request from a provisioner.
 *
 * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#
 */
public class WorkerTypeResponse {

    public class WorkerTypeAction {

        /**
         * Only actions with the context `worker-type` are included.
         *
         * Possible values:
         *     * "worker-type"
         *
         * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions/items/properties/context
         */
        public String context;

        /**
         * Description of the provisioner.
         *
         * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions/items/properties/description
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
         * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions/items/properties/method
         */
        public String method;

        /**
         * Short names for things like logging/error messages.
         *
         * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions/items/properties/name
         */
        public String name;

        /**
         * Appropriate title for any sort of Modal prompt.
         *
         * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions/items/properties/title
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
         * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions/items/properties/url
         */
        public String url;
    }

    /**
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/actions
     */
    public WorkerTypeAction[] actions;

    /**
     * Description of the worker-type.
     *
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/description
     */
    public String description;

    /**
     * Date and time after which the worker-type will be automatically
     * deleted by the queue.
     *
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Date of the last time this worker-type was seen active. `lastDateActive` is updated every 6 hours
     * but may be off by up-to 6 hours. Nonetheless, `lastDateActive` is a good indicator
     * of when the worker-type was last seen active.
     *
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/lastDateActive
     */
    public Date lastDateActive;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * This is the stability of the worker-type. Accepted values:
     *   * `experimental`
     *   * `stable`
     *   * `deprecated`
     *
     * Possible values:
     *     * "experimental"
     *     * "stable"
     *     * "deprecated"
     *
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/stability
     */
    public String stability;

    /**
     * WorkerType name.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See https://schemas.taskcluster.net/queue/v1/workertype-response.json#/properties/workerType
     */
    public String workerType;
}
