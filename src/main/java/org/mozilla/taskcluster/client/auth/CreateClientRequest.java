package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Properties to create a client.
 *
 * See https://schemas.taskcluster.net/auth/v1/create-client-request.json#
 */
public class CreateClientRequest {

    /**
     * If `true`, the service may delete this client after it has expired.  If
     * `false` (the default), the client will remain after expiration, although
     * it cannot be used for authentication in that state.
     *
     * Default:    false
     *
     * See https://schemas.taskcluster.net/auth/v1/create-client-request.json#/properties/deleteOnExpiration
     */
    public boolean deleteOnExpiration;

    /**
     * Description of what these credentials are used for in markdown.
     * Should include who is the owner, point of contact.
     *
     * Max length: 10240
     *
     * See https://schemas.taskcluster.net/auth/v1/create-client-request.json#/properties/description
     */
    public String description;

    /**
     * Date and time where the clients access is set to expire
     *
     * See https://schemas.taskcluster.net/auth/v1/create-client-request.json#/properties/expires
     */
    public Date expires;

    /**
     * List of scopes the client has (unexpanded).
     *
     * See https://schemas.taskcluster.net/auth/v1/create-client-request.json#/properties/scopes
     */
    public String[] scopes;
}
