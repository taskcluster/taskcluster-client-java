package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Secrets from the provisioner
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#
 */
public class GetSecretResponse {

    public class Credentials {

        /**
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#/properties/credentials/properties/accessToken
         */
        public String accessToken;

        /**
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#/properties/credentials/properties/certificate
         */
        public String certificate;

        /**
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#/properties/credentials/properties/clientId
         */
        public String clientId;
    }

    /**
     * Generated Temporary credentials from the Provisioner
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#/properties/credentials
     */
    public Credentials credentials;

    /**
     * Free-form object which contains secrets from the worker type definition
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#/properties/data
     */
    public Object data;
}
