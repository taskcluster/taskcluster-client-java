package org.mozilla.taskcluster.client.auth;

/**
 * Response from a request to authenticate a hawk request.
 *
 * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/oneOf[1]
 */
public class AuthenticationFailedResponse {

    /**
     * Message saying why the authentication failed.
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/oneOf[1]/properties/message
     */
    public String message;

    /**
     * The kind of response, `auth-failed` or `auth-success`.
     *
     * Possible values:
     *     * "auth-failed"
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/oneOf[1]/properties/status
     */
    public String status;
}
