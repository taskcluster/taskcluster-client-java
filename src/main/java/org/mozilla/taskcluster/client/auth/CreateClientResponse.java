package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * All details about a client including the `accessToken`
 *
 * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#
 */
public class CreateClientResponse {

    /**
     * AccessToken used for authenticating requests, you should store this
     * you won't be able to retrive it again!
     *
     * Syntax:     ^[a-zA-Z0-9_-]{22,66}$
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/accessToken
     */
    public String accessToken;

    /**
     * ClientId of the client
     *
     * Syntax:     ^[A-Za-z0-9@/:._-]+$
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/clientId
     */
    public String clientId;

    /**
     * Date and time when this client was created
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/created
     */
    public Date created;

    /**
     * If `true`, the service may delete this client after it has expired.  If
     * `false`, the client will remain after expiration, although it cannot be
     * used for authentication in that state.
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/deleteOnExpiration
     */
    public boolean deleteOnExpiration;

    /**
     * Description of what these credentials are used for in markdown.
     * Should include who is the owner, point of contact.
     *
     * Max length: 10240
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/description
     */
    public String description;

    /**
     * If true, this client is disabled and cannot be used.  This usually occurs when the
     * scopes available to the user owning the client no longer satisfy the client.
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/disabled
     */
    public boolean disabled;

    /**
     * List of scopes granted to this client by matching roles, including the
     * client's scopes and the implicit role `client-id:<clientId>`.
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/expandedScopes
     */
    public String[] expandedScopes;

    /**
     * Date and time where the clients access is set to expire
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Date of last time this client was used. Will only be updated every 6 hours
     * or so this may be off by up-to 6 hours. But it still gives a solid hint
     * as to whether or not this client is in use.
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/lastDateUsed
     */
    public Date lastDateUsed;

    /**
     * Date and time of last modification
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/lastModified
     */
    public Date lastModified;

    /**
     * Date and time of when the `accessToken` was reset last time.
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/lastRotated
     */
    public Date lastRotated;

    /**
     * List of scopes the client has (unexpanded).  Scopes must be composed of
     * printable ASCII characters and spaces.
     *
     * Default:    []
     *
     * See http://schemas.taskcluster.net/auth/v1/create-client-response.json#/properties/scopes
     */
    public String[] scopes;
}
