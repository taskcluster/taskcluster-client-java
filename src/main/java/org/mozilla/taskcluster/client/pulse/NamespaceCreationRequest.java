package org.mozilla.taskcluster.client.pulse;

/**
 * Namespace creation request
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#
 */
public class NamespaceCreationRequest {

    public class Contact {

        /**
         * The contact id (eg. username, email address)
         *
         * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/contact/properties/id
         */
        public String id;

        /**
         * The contact method (eg. irc, email)
         *
         * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/contact/properties/method
         */
        public String method;
    }

    /**
     * The contact Information
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/contact
     */
    public Contact contact;
}
