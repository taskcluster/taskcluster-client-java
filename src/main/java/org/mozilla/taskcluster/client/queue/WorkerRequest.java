package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request to update a worker.
 *
 * See https://schemas.taskcluster.net/queue/v1/update-worker-request.json#
 */
public class WorkerRequest {

    /**
     * Date and time after which the worker will be automatically
     * deleted by the queue.
     *
     * See https://schemas.taskcluster.net/queue/v1/update-worker-request.json#/properties/expires
     */
    public Date expires;
}
