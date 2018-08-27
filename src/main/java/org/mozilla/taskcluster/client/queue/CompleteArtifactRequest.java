package org.mozilla.taskcluster.client.queue;

/**
 * Complete an aritifact
 *
 * See https://schemas.taskcluster.net/queue/v1/put-artifact-request.json#
 */
public class CompleteArtifactRequest {

    /**
     * A list of the etags given by the API of the blob storage provider.  This is an opaque
     * string value provided by the API.
     *
     * See https://schemas.taskcluster.net/queue/v1/put-artifact-request.json#/properties/etags
     */
    public String[] etags;
}
