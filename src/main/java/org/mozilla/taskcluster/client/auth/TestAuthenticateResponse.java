package org.mozilla.taskcluster.client.auth;

/**
 * Details on how the test request was authenticated.
 *
 * See https://schemas.taskcluster.net/auth/v1/test-authenticate-response.json#
 */
public class TestAuthenticateResponse {

    /**
     * ClientId from the request as it will be logged
     *
     * Syntax:     ^[A-Za-z0-9!@/:.+|_-]+$
     *
     * See https://schemas.taskcluster.net/auth/v1/test-authenticate-response.json#/properties/clientId
     */
    public String clientId;

    /**
     * List of scopes the request was authorized.
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/auth/v1/test-authenticate-response.json#/properties/scopes
     */
    public String[] scopes;
}
