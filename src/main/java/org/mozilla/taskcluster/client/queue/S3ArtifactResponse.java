package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response to a request for a signed PUT URL that will allow you to
 * upload an artifact to an S3 bucket managed by the queue.
 *
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]
 */
public class S3ArtifactResponse {

    /**
     * Artifact mime-type, must be specified as header when uploading with
     * the signed `putUrl`.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/contentType
     */
    public String contentType;

    /**
     * Date-time after which the signed `putUrl` no longer works
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/expires
     */
    public Date expires;

    /**
     * URL to which a `PUT` request can be made to upload the artifact
     * requested. Note, the `Content-Length` must be specified correctly,
     * and the `ContentType` header must be set the value specified below.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/putUrl
     */
    public String putUrl;

    /**
     * Artifact storage type, in this case `'s3'`
     *
     * Possible values:
     *     * "s3"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/storageType
     */
    public String storageType;
}
