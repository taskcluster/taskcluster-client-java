package org.mozilla.taskcluster.client.auth;

/**
* Details on how the test request was authenticated.
*
* See http://schemas.taskcluster.net/auth/v1/test-authenticate-response.json#
*/
public class TestAuthenticateResponse {

    /**
     * ClientId from the request as it will be logged
     */
    public String clientId;

    /**
     * List of scopes the request was authorized.
     */
    public String[] scopes;
}