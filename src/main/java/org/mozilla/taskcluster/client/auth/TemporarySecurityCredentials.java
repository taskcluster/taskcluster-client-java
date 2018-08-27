package org.mozilla.taskcluster.client.auth;

/**
 * Temporary STS credentials for use when operating on S3
 *
 * See https://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials
 */
public class TemporarySecurityCredentials {

    /**
     * Access key identifier that identifies the temporary security
     * credentials.
     *
     * See https://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials/properties/accessKeyId
     */
    public String accessKeyId;

    /**
     * Secret access key used to sign requests
     *
     * See https://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials/properties/secretAccessKey
     */
    public String secretAccessKey;

    /**
     * A token that must passed with request to use the temporary
     * security credentials.
     *
     * See https://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials/properties/sessionToken
     */
    public String sessionToken;
}
