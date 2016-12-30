package org.mozilla.taskcluster.client.login;

/**
 * A response containing temporary credentials.
 *
 * See http://schemas.taskcluster.net/login/v1/credentials-response.json#
 */
public class CredentialsResponse {

    /**
     *
     * Syntax:     ^[a-zA-Z0-9_-]{22,66}$
     *
     * See http://schemas.taskcluster.net/login/v1/credentials-response.json#/properties/accessToken
     */
    public String accessToken;

    /**
     * See http://schemas.taskcluster.net/login/v1/credentials-response.json#/properties/certificate
     */
    public String certificate;

    /**
     *
     * Syntax:     ^[A-Za-z0-9@/:._-]+$
     *
     * See http://schemas.taskcluster.net/login/v1/credentials-response.json#/properties/clientId
     */
    public String clientId;
}
