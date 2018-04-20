package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Information about an artifact for the given `taskId` and `runId`.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items
 */
public class Artifact {

    /**
     * Mimetype for the artifact that was created.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/contentType
     */
    public String contentType;

    /**
     * Date and time after which the artifact created will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/expires
     */
    public Date expires;

    /**
     * Name of the artifact that was created, this is useful if you want to
     * attempt to fetch the artifact.
     *
     * Max length: 1024
     *
     * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/name
     */
    public String name;

    /**
     * This is the `storageType` for the request that was used to create
     * the artifact.
     *
     * Possible values:
     *     * "blob"
     *     * "s3"
     *     * "azure"
     *     * "reference"
     *     * "error"
     *
     * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/storageType
     */
    public String storageType;
}
