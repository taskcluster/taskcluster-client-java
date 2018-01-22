package org.mozilla.taskcluster.client.auth;

/**
 * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[1]
 */
public class AuthenticationFailedResponse {

    /**
     * Message saying why the authentication failed.
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[1]/properties/message
     */
    public String message;

    /**
     * The kind of response, `auth-failed` or `auth-success`.
     *
     * Possible values:
     *     * "auth-failed"
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[1]/properties/status
     */
    public String status;
}
