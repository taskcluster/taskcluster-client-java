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
         * E-mail address that will reach people who can address problems with runaway queue length.
         * The service will send warning notifications to this address before forcibly deleting the
         * queue.  If omitted, no warnings will be given.
         *
         * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces/items/properties/contact
         */
        public String contact;

        /**
         * Date-time at which this namespace was first claimed.
         *
         * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces/items/properties/created
         */
        public Date created;

        /**
         * Date-time after which the username, and all associated queues and
         * exchanges, should be deleted.
         *
         * See http://schemas.taskcluster.net/pulse/v1/list-namespaces-response.json#/properties/namespaces/items/properties/expires
         */
        public Date expires;

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
