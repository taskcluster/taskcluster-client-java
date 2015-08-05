package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
* Response for a request to get access to an S3 bucket.
*
* See http://schemas.taskcluster.net/auth/v1/aws-s3-credentials-response.json#
*/
public class AWSS3CredentialsResponse {

    /**
     * Temporary STS credentials for use when operating on S3
     */
    public class Credentials {

        /**
         * Access key identifier that identifies the temporary security
         * credentials.
         */
        public String accessKeyId;

        /**
         * Secret access key used to sign requests
         */
        public String secretAccessKey;

        /**
         * A token that must passed with request to use the temporary
         * security credentials.
         */
        public String sessionToken;
    }

    public Credentials credentials;

    /**
     * Date and time of when the temporary credentials expires.
     */
    public Date expires;
}