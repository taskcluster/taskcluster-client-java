package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

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
     * Date-time at which this namespace was first claimed.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/created
     */
    public Date created;

    /**
     * Date-time after which the username, and all associated queues and
     * exchanges, should be deleted.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/expires
     */
    public Date expires;

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
     * The caller should plan to call `claimNamespace` again at this time. The provided
     * password will become invalid a short time after this.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/reclaimAt
     */
    public Date reclaimAt;

    /**
     * The username created for authentication
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/username
     */
    public String username;
}
