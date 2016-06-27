package org.mozilla.taskcluster.client.index;

/**
 * Request to list namespaces within a given namespace.
 *
 * See http://schemas.taskcluster.net/index/v1/list-namespaces-request.json#
 */
public class ListNamespacesRequest {

    /**
     * A continuation token previously returned in a response to this list
     * request. This property is optional and should not be provided for first
     * requests.
     *
     * See http://schemas.taskcluster.net/index/v1/list-namespaces-request.json#/properties/continuationToken
     */
    public String continuationToken;

    /**
     * Maximum number of results per page. If there are more results than this
     * a continuation token will be return.
     *
     * Default:    1000
     * Mininum:    1
     * Maximum:    1000
     *
     * See http://schemas.taskcluster.net/index/v1/list-namespaces-request.json#/properties/limit
     */
    public int limit;
}
