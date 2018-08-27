package org.mozilla.taskcluster.client.queue;

/**
 * Response to a request for the queue to redirect to a URL for a given
 * artifact.
 *
 * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[3]
 */
public class RedirectArtifactResponse {

    /**
     * Artifact storage type, in this case `reference`
     *
     * Possible values:
     *     * "reference"
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[3]/properties/storageType
     */
    public String storageType;
}
