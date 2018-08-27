package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response to a request for creating a new blob artifact
 *
 * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]
 */
public class BlobArtifactResponse {

    /**
     * Date-time after which the signed `requests` no longer work
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/expires
     */
    public Date expires;

    public class HTTPRequest {

        /**
         * Headers of request
         *
         * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items/properties/headers
         */
        public Object headers;

        /**
         * HTTP 1.1 method of request
         *
         * Possible values:
         *     * "GET"
         *     * "POST"
         *     * "PUT"
         *     * "DELETE"
         *     * "OPTIONS"
         *     * "HEAD"
         *     * "PATCH"
         *
         * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items/properties/method
         */
        public String method;

        /**
         * URL of request
         *
         * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items/properties/url
         */
        public String url;
    }

    /**
     * A list of generalized HTTP requests which must be run to upload the
     * artifact.
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests
     */
    public HTTPRequest[] requests;

    /**
     * Artifact storage type, in this case `'blob'`
     *
     * Possible values:
     *     * "blob"
     *
     * See https://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/storageType
     */
    public String storageType;
}
