package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response from a `listWorkers` request.
 *
 * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#
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
     * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/continuationToken
     */
    public String continuationToken;

    public class Worker {

        /**
         * Date of the first time this worker claimed a task.
         *
         * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/firstClaim
         */
        public Date firstClaim;

        /**
         * The most recent claimed task
         *
         * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/latestTask
         */
        public TaskRun latestTask;

        /**
         * Quarantining a worker allows the machine to remain alive but not accept jobs.
         * Once the quarantineUntil time has elapsed, the worker resumes accepting jobs.
         * Note that a quarantine can be lifted by setting `quarantineUntil` to the present time (or
         * somewhere in the past).
         *
         * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/quarantineUntil
         */
        public Date quarantineUntil;

        /**
         * Identifier for the worker group containing this worker.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/workerGroup
         */
        public String workerGroup;

        /**
         * Identifier for this worker (unique within this worker group).
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers/items/properties/workerId
         */
        public String workerId;
    }

    /**
     * List of workers in this worker-type.
     *
     * See https://schemas.taskcluster.net/queue/v1/list-workers-response.json#/properties/workers
     */
    public Worker[] workers;
}
