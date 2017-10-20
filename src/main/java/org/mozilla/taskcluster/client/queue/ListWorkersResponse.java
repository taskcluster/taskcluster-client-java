package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response from a `listWorkers` request.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#
 */
public class ListWorkersResponse {

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
         *     * "worker"
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/actions/items/properties/context
         */
        public String context;

        /**
         * Description of the provisioner.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/actions/items/properties/description
         */
        public String description;

        /**
         * Short names for things like logging/error messages.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/actions/items/properties/name
         */
        public String name;

        /**
         * Appropriate title for any sort of Modal prompt.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/actions/items/properties/title
         */
        public Object title;

        /**
         * When an action is triggered, the `url`
         * and `context` property are used to make the `POST` request.
         * The request needs to be signed with the user's Taskcluster credentials.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/actions/items/properties/url
         */
        public String url;
    }

    /**
     * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/actions
     */
    public Actions1[] actions;

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of workers in the worker-type.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called `listWorkerTypes` with `continuationToken`
     * until you get a result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/continuationToken
     */
    public String continuationToken;

    public class WorkersEntry {

        /**
         * Disabling a worker allows the machine to remain alive but not accept jobs.
         * Enabling a worker on the other hand will resume accepting jobs.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/disabled
         */
        public boolean disabled;

        /**
         * Date of the first time this worker claimed a task.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/firstClaim
         */
        public Date firstClaim;

        public class MostRecentTask {

            /**
             * Id of this task run, `run-id`s always starts from `0`
             *
             * Mininum:    0
             * Maximum:    1000
             *
             * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/latestTask/properties/runId
             */
            public int runId;

            /**
             * Unique task identifier, this is UUID encoded as
             * [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and
             * stripped of `=` padding.
             *
             * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
             *
             * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/latestTask/properties/taskId
             */
            public String taskId;
        }

        /**
         * The most recent claimed task
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/latestTask
         */
        public MostRecentTask latestTask;

        /**
         * Identifier for the worker group containing this worker.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/workerGroup
         */
        public String workerGroup;

        /**
         * Identifier for this worker (unique within this worker group).
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/workerId
         */
        public String workerId;
    }

    /**
     * List of workers in this worker-type.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers
     */
    public WorkersEntry[] workers;
}
