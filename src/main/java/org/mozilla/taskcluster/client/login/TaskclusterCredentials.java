package org.mozilla.taskcluster.client.login;

/**
 * Taskcluster credentials. Note that the credentials may not contain a certificate!
 *
 * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#/properties/credentials
 */
public class TaskclusterCredentials {

    /**
     *
     * Syntax:     ^[a-zA-Z0-9_-]{22,66}$
     *
     * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#/properties/credentials/properties/accessToken
     */
    public String accessToken;

    /**
     * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#/properties/credentials/properties/certificate
     */
    public String certificate;

    /**
     *
     * Syntax:     ^[A-Za-z0-9!@/:.+|_-]+$
     *
     * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#/properties/credentials/properties/clientId
     */
    public String clientId;
}
