package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

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
         * Date-time at which this namespace was first claimed.
         *
         * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces/items/properties/created
         */
        public Date created;

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
