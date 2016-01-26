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
     */
    public String accessToken;

    /**
     * ClientId of the client
     */
    public String clientId;

    /**
     * Date and time when this client was created
     */
    public Date created;

    /**
     * Description of what these credentials are used for in markdown.
     * Should include who is the owner, point of contact.
     */
    public String description;

    /**
     * If true, this client is disabled and cannot be used.  This usually occurs when the
     * scopes avaialble to the user owning the client no longer satisfy the client.
     */
    public boolean disabled;

    /**
     * List of scopes granted to this client by matching roles, including the
     * client's scopes and the implicit role `client-id:<clientId>`.
     */
    public String[] expandedScopes;

    /**
     * Date and time where the clients access is set to expire
     */
    public Date expires;

    /**
     * Date of last time this client was used. Will only be updated every 6 hours
     * or so this may be off by up-to 6 hours. But it still gives a solid hint
     * as to whether or not this client is in use.
     */
    public Date lastDateUsed;

    /**
     * Date and time of last modification
     */
    public Date lastModified;

    /**
     * Date and time of when the `accessToken` was reset last time.
     */
    public Date lastRotated;

    /**
     * List of scopes the client has (unexpanded).  Scopes must be composed of
     * printable ASCII characters and spaces.
     */
    public String[] scopes;
}