package org.mozilla.taskcluster.client.pulse;

/**
 * Response from a request to list namespaces
 *
 * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#
 */
public class ListNamespacesResponse {

    /**
     * A continuation token is returned if there are more results than listed
     * here. You can optionally provide the token as a query parameter to load
     * the additional results.
     *
     * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/continuationToken
     */
    public String continuationToken;

    public class Namespace {

        /**
         * The contact information which will be handed off to the notification service
         *
         * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces/items/properties/contact
         */
        public Object contact;

        /**
         * The namespace's name
         *
         * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces/items/properties/namespace
         */
        public String namespace;
    }

    /**
     * List of namespaces.
     *
     * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces
     */
    public Namespace[] namespaces;
}
