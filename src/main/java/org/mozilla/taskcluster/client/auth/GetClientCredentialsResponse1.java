package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
* Credentials, scopes and expiration date for a client
*
* See http://schemas.taskcluster.net/auth/v1/create-client-request.json#
*/
public class GetClientCredentialsResponse1 {

    /**
     * Description of what these credentials are used for in markdown.
     * Please write a few details here, including who is the owner, point of
     * contact. Why it is scoped as is, think of this as documentation.
     */
    public String description;

    /**
     * Date and time where the clients credentials are set to expire
     */
    public Date expires;

    /**
     * Human readable name of this set of credentials, typical
     * component/server-name or IRC nickname of the user.
     */
    public String name;

    /**
     * List of scopes the client is authorized to access
     */
    public String[] scopes;
}