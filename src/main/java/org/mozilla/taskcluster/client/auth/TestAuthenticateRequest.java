package org.mozilla.taskcluster.client.auth;

/**
 * Details on how the test request should be authenticated.
 *
 * See https://schemas.taskcluster.net/auth/v1/test-authenticate-request.json#
 */
public class TestAuthenticateRequest {

    /**
     * List of scopes that should be client used should be given.
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/auth/v1/test-authenticate-request.json#/properties/clientScopes
     */
    public String[] clientScopes;

    /**
     * List of scopes the request should require.
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/auth/v1/test-authenticate-request.json#/properties/requiredScopes
     */
    public String[] requiredScopes;
}
