package org.mozilla.taskcluster.client.queue;

/**
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items
 */
public class HTTPRequest {

    /**
     * Headers of request
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items/properties/headers
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
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items/properties/method
     */
    public String method;

    /**
     * URL of request
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-response.json#/oneOf[0]/properties/requests/items/properties/url
     */
    public String url;
}
