package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request the queue to redirect to a URL for a given artifact.
 * This allows you to reference artifacts that aren't managed by the queue.
 * The queue will still authenticate the request, so depending on the level
 * of secrecy required, secret URLs **might** work. Note, this is mainly
 * useful for public artifacts, for example temporary files directly
 * stored on the worker host and only available there for a specific
 * amount of time.
 *
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[2]
 */
public class RedirectArtifactRequest {

    /**
     * Artifact mime-type for the resource to which the queue should
     * redirect. Please use the same `Content-Type`, consistently using
     * the correct mime-type make tooling a lot easier, specifically,
     * always using `application/json` for JSON artifacts.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[2]/properties/contentType
     */
    public String contentType;

    /**
     * Date-time after which the queue should no longer redirect to this URL.
     * Note, that the queue will and cannot delete the resource your URL
     * references, you are responsible for doing that yourself.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[2]/properties/expires
     */
    public Date expires;

    /**
     * Artifact storage type, in this case `reference`
     *
     * Possible values:
     *     * "reference"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[2]/properties/storageType
     */
    public String storageType;

    /**
     * URL to which the queue should redirect using a `303` (See other)
     * redirect.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[2]/properties/url
     */
    public String url;
}
