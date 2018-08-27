package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
 * Information about the artifact that was created
 *
 * See https://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact
 */
public class Artifact {

    /**
     * Mimetype for the artifact that was created.
     *
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/contentType
     */
    public String contentType;

    /**
     * Date and time after which the artifact created will be automatically
     * deleted by the queue.
     *
     * See https://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/expires
     */
    public Date expires;

    /**
     * Name of the artifact that was created, this is useful if you want to
     * attempt to fetch the artifact. But keep in mind that just because an
     * artifact is created doesn't mean that it's immediately available.
     *
     * Max length: 1024
     *
     * See https://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/name
     */
    public String name;

    /**
     * This is the `storageType` for the request that was used to create the
     * artifact.
     *
     * Possible values:
     *     * "blob"
     *     * "reference"
     *     * "error"
     *
     * See https://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/storageType
     */
    public String storageType;
}
