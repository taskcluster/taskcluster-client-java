package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request to update a worker's quarantineUntil property.
 *
 * See https://schemas.taskcluster.net/queue/v1/quarantine-worker-request.json#
 */
public class QuarantineWorkerRequest {

    /**
     * Quarantining a worker allows the machine to remain alive but not accept jobs.
     * Once the quarantineUntil time has elapsed, the worker resumes accepting jobs.
     * Note that a quarantine can be lifted by setting `quarantineUntil` to the present time (or
     * somewhere in the past).
     *
     * See https://schemas.taskcluster.net/queue/v1/quarantine-worker-request.json#/properties/quarantineUntil
     */
    public Date quarantineUntil;
}
