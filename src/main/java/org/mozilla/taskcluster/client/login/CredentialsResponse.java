package org.mozilla.taskcluster.client.login;

import java.util.Date;

/**
 * A response containing credentials corresponding to a supplied OIDC `access_token`.
 *
 * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#
 */
public class CredentialsResponse {

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

    /**
     * Taskcluster credentials. Note that the credentials may not contain a certificate!
     *
     * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#/properties/credentials
     */
    public TaskclusterCredentials credentials;

    /**
     * Time after which the credentials are no longer valid.  Callers should
     * call `oidcCredentials` again to get fresh credentials before this time.
     *
     * See https://schemas.taskcluster.net/login/v1/oidc-credentials-response.json#/properties/expires
     */
    public Date expires;
}
