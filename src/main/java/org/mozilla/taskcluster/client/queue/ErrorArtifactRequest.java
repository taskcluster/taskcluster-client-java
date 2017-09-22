package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request the queue to reply `403` (forbidden) with `reason` and `message`
 * to any `GET` request for this artifact. This is mainly useful as a way
 * for a task to declare that it failed to provide an artifact it wanted
 * to upload.
 *
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[4]
 */
public class ErrorArtifactRequest {

    /**
     * Date-time after which the queue should stop replying with the error
     * and forget about the artifact.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[4]/properties/expires
     */
    public Date expires;

    /**
     * Human readable explanation of why the artifact is missing
     *
     * Max length: 4096
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[4]/properties/message
     */
    public String message;

    /**
     * Reason why the artifact doesn't exist.
     *
     * Possible values:
     *     * "file-missing-on-worker"
     *     * "invalid-resource-on-worker"
     *     * "too-large-file-on-worker"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[4]/properties/reason
     */
    public String reason;

    /**
     * Artifact storage type, in this case `error`
     *
     * Possible values:
     *     * "error"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[4]/properties/storageType
     */
    public String storageType;
}
