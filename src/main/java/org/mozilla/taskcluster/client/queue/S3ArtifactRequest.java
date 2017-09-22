package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request for a signed PUT URL that will allow you to upload an artifact
 * to an S3 bucket managed by the queue.
 *
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]
 */
public class S3ArtifactRequest {

    /**
     * Artifact mime-type, when uploading artifact to the signed
     * `PUT` URL returned from this request this must given with the
     *  `ContentType` header. Please, provide correct mime-type,
     *  this make tooling a lot easier, specifically,
     *  always using `application/json` for JSON artifacts.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]/properties/contentType
     */
    public String contentType;

    /**
     * Date-time after which the artifact should be deleted. Note, that
     * these will be collected over time, and artifacts may remain
     * available after expiration. S3 based artifacts are identified in
     * azure table storage and explicitly deleted on S3 after expiration.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]/properties/expires
     */
    public Date expires;

    /**
     * Artifact storage type, in this case `'s3'`
     *
     * Possible values:
     *     * "s3"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[1]/properties/storageType
     */
    public String storageType;
}
