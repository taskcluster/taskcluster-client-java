package org.mozilla.taskcluster.client.pulse;

/**
 * Namespace creation response
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#
 */
public class NamespaceCreationResponse {

    /**
     * The contact information which will be handed off to the notification service
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/contact
     */
    public Object contact;

    /**
     * The name of the namespace created
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/namespace
     */
    public String namespace;

    /**
     * The password created for authentication
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/password
     */
    public String password;

    /**
     * The username created for authentication
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/username
     */
    public String username;
}
