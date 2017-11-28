package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response from a `listWorkers` request.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#
 */
public class ListWorkersResponse {

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
         * Quarantining a worker allows the machine to remain alive but not accept jobs.
         * Once the quarantineUntil time has elapsed, the worker resumes accepting jobs.
         * Note that a quarantine can be lifted by setting `quarantineUntil` to the present time (or
         * somewhere in the past).
         *
         * See http://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/quarantineUntil
         */
        public Date quarantineUntil;

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
