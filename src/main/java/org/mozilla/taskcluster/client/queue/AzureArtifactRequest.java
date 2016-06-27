package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request for an Azure Shared Access Signature (SAS) that will allow
 * you to upload an artifact to an Azure blob storage container managed
 * by the queue.
 *
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]
 */
public class AzureArtifactRequest {

    /**
     * Artifact mime-type, when uploading artifact please use the same
     * `Content-Type`, consistently using the correct mime-type make
     * tooling a lot easier, specifically, always using `application/json`
     * for JSON artifacts.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]/properties/contentType
     */
    public String contentType;

    /**
     * Date-time after which the artifact should be deleted.
     * Note, that these will be collected over time, and artifacts may
     * remain available after expiration. Azure based artifacts are
     * identified in azure table storage and explicitly deleted in the
     * azure storage container after expiration.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]/properties/expires
     */
    public Date expires;

    /**
     * Artifact storage type, in this case `azure`
     *
     * Possible values:
     *     * "azure"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]/properties/storageType
     */
    public String storageType;
}
