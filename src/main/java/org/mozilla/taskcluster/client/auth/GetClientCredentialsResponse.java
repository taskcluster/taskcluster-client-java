package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
* Credentials, scopes and expiration date for a client
*
* See http://schemas.taskcluster.net/auth/v1/client-credentials-response.json#
*/
public class GetClientCredentialsResponse {

    /**
     * AccessToken used for authenticating requests
     */
    public String accessToken;

    /**
     * ClientId of the client scopes is requested about
     */
    public String clientId;

    /**
     * Date and time where the clients credentials are set to expire
     */
    public Date expires;

    /**
     * List of scopes the client is authorized to access
     */
    public String[] scopes;
}