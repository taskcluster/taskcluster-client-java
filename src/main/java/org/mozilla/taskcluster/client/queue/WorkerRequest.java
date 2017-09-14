package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request to update a worker.
 *
 * See http://schemas.taskcluster.net/queue/v1/update-worker-request.json#
 */
public class WorkerRequest {

    /**
     * Disabling a worker allows the machine to remain alive but not accept jobs.
     * Enabling a worker on the other hand will resume accepting jobs.
     *
     * See http://schemas.taskcluster.net/queue/v1/update-worker-request.json#/properties/disabled
     */
    public boolean disabled;

    /**
     * Date and time after which the worker will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/update-worker-request.json#/properties/expires
     */
    public Date expires;
}
