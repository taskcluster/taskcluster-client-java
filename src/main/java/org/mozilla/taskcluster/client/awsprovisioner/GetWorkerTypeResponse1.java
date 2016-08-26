package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
 * Get the last modified date of a workerType
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-last-modified.json#
 */
public class GetWorkerTypeResponse1 {

    /**
     * ISO Date string (e.g. new Date().toISOString()) which represents the time
     * when this worker type definition was last altered (inclusive of creation)
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-last-modified.json#/properties/lastModified
     */
    public Date lastModified;

    /**
     * The ID of the workerType
     *
     * Syntax:     ^[A-Za-z0-9+/=_-]{1,22}$
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-last-modified.json#/properties/workerType
     */
    public String workerType;
}
