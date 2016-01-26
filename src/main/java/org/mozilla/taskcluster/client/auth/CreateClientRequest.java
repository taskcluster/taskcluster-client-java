package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
* Properties to create a client.
*
* See http://schemas.taskcluster.net/auth/v1/create-client-request.json#
*/
public class CreateClientRequest {

    /**
     * Description of what these credentials are used for in markdown.
     * Should include who is the owner, point of contact.
     */
    public String description;

    /**
     * Date and time where the clients access is set to expire
     */
    public Date expires;

    /**
     * List of scopes the client has.  Scopes must be composed of
     * printable ASCII characters and spaces.
     */
    public String[] scopes;
}