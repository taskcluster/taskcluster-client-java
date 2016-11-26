package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Response for a request to get access to an S3 bucket.
 *
 * See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#
 */
public class AWSS3CredentialsResponse {

    public class TemporarySecurityCredentials {

        /**
         * Access key identifier that identifies the temporary security
         * credentials.
         *
         * See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials/properties/accessKeyId
         */
        public String accessKeyId;

        /**
         * Secret access key used to sign requests
         *
         * See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials/properties/secretAccessKey
         */
        public String secretAccessKey;

        /**
         * A token that must passed with request to use the temporary
         * security credentials.
         *
         * See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials/properties/sessionToken
         */
        public String sessionToken;
    }

    /**
     * Temporary STS credentials for use when operating on S3
     *
     * See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/credentials
     */
    public TemporarySecurityCredentials credentials;

    /**
     * Date and time of when the temporary credentials expires.
     *
     * See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#/properties/expires
     */
    public Date expires;
}
