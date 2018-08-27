package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response to a request for an Azure Shared Access Signature (SAS)
 * that will allow you to upload an artifact to an Azure blob storage
 * container managed by the queue.
 *
 * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[2]
 */
public class AzureArtifactResponse {

    /**
     * Artifact mime-type, should be specified with the
     * `x-ms-blob-content-type` when committing the block.
     *
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[2]/properties/contentType
     */
    public String contentType;

    /**
     * Date-time after which Shared Access Signature (SAS) will
     * seize to work.
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[2]/properties/expires
     */
    public Date expires;

    /**
     * Shared Access Signature (SAS) with write permissions, see
     * [Azure REST API]
     * (http://msdn.microsoft.com/en-US/library/azure/dn140256.aspx)
     * reference for details on how to use this.
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[2]/properties/putUrl
     */
    public String putUrl;

    /**
     * Artifact storage type, in this case `azure`
     *
     * Possible values:
     *     * "azure"
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[2]/properties/storageType
     */
    public String storageType;
}
