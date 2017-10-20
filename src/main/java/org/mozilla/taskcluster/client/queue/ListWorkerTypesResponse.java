package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response from a `listWorkerTypes` request.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#
 */
public class ListWorkerTypesResponse {

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
         *     * "worker-type"
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/actions/items/properties/context
         */
        public String context;

        /**
         * Description of the provisioner.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/actions/items/properties/description
         */
        public String description;

        /**
         * Short names for things like logging/error messages.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/actions/items/properties/name
         */
        public String name;

        /**
         * Appropriate title for any sort of Modal prompt.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/actions/items/properties/title
         */
        public Object title;

        /**
         * When an action is triggered, the `url`
         * and `context` property are used to make the `POST` request.
         * The request needs to be signed with the user's Taskcluster credentials.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/actions/items/properties/url
         */
        public String url;
    }

    /**
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/actions
     */
    public Actions1[] actions;

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of worker-types in the provisioner.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called `listWorkerTypes` with `continuationToken`
     * until you get a result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/continuationToken
     */
    public String continuationToken;

    public class WorkerTypesEntry {

        /**
         * Description of the worker-type.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/description
         */
        public String description;

        /**
         * Date and time after which the worker-type will be automatically
         * deleted by the queue.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/expires
         */
        public Date expires;

        /**
         * Date and time where the worker-type was last seen active
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/lastDateActive
         */
        public Date lastDateActive;

        /**
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/provisionerId
         */
        public String provisionerId;

        /**
         * This is the stability of the worker-type. Accepted values:
         *  * `experimental`
         *  * `stable`
         *  * `deprecated`
         *
         * Possible values:
         *     * "experimental"
         *     * "stable"
         *     * "deprecated"
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/stability
         */
        public String stability;

        /**
         * WorkerType name.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/workerType
         */
        public String workerType;
    }

    /**
     * List of worker-types in this provisioner.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes
     */
    public WorkerTypesEntry[] workerTypes;
}
