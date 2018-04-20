package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Generated Temporary credentials from the Provisioner
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#/properties/credentials
 */
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
