package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response containing information about a worker.
 *
 * See http://schemas.taskcluster.net/queue/v1/worker-response.json#
 */
public class WorkerResponse {

    /**
     * Disabling a worker allows the machine to remain alive but not accept jobs.
     * Enabling a worker on the other hand will resume accepting jobs.
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/disabled
     */
    public boolean disabled;

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
     * 20 most recent task Ids claimed by the worker.
     *
     * See http://schemas.taskcluster.net/queue/v1/worker-response.json#/properties/recentTasks
     */
    public String[] recentTasks;

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
