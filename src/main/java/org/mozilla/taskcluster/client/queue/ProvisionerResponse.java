package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response containing information about a provisioner.
 *
 * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#
 */
public class ProvisionerResponse {

    public class Actions1 {

        /**
         * Actions have a "context" that is one of provisioner, worker-type,
         * or worker, indicating which it applies to. `context` is used to construct
         * the query string of the `POST` request.
         * If `context='worker'`, the query string will be
         * `?provisionerId=${PROVISIONER_ID}&workerType=${WORKER_TYPE}&workerGroup=${WORKER_GROUP}&workerId=${WORKER_ID}`.
         * If `context='worker-type'`, the query string will be
         * `?provisionerId=${PROVISIONER_ID}&workerType=${WORKER_TYPE}`.
         * If `context='provisioner'`, the query string will be
         * `?provisionerId=${PROVISIONER_ID}`.
         *
         * Possible values:
         *     * "provisioner"
         *     * "worker-type"
         *     * "worker"
         *
         * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/actions/items/properties/context
         */
        public String context;

        /**
         * Description of the provisioner.
         *
         * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/actions/items/properties/description
         */
        public String description;

        /**
         * Short names for things like logging/error messages.
         *
         * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/actions/items/properties/name
         */
        public String name;

        /**
         * Appropriate title for any sort of Modal prompt.
         *
         * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/actions/items/properties/title
         */
        public Object title;

        /**
         * When an action is triggered, the `url`
         * and `context` property are used to make the `POST` request.
         * The request needs to be signed with the user's Taskcluster credentials.
         *
         * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/actions/items/properties/url
         */
        public String url;
    }

    /**
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/actions
     */
    public Actions1[] actions;

    /**
     * Description of the provisioner.
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/description
     */
    public String description;

    /**
     * Date and time after which the provisioner will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Date of the last time this provisioner was seen active. `lastDateActive` is updated every 6 hours
     * but may be off by up-to 6 hours. Nonetheless, `lastDateActive` is a good indicator
     * of when the provisioner was last seen active.
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/lastDateActive
     */
    public Date lastDateActive;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * This is the stability of the provisioner. Accepted values:
     *   * `experimental`
     *   * `stable`
     *   * `deprecated`
     *
     * Possible values:
     *     * "experimental"
     *     * "stable"
     *     * "deprecated"
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/stability
     */
    public String stability;
}
