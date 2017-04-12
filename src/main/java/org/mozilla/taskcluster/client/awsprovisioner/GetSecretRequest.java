package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
 * A Secret
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#
 */
public class GetSecretRequest {

    /**
     * The date at which the secret is no longer guaranteed to exist
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#/properties/expiration
     */
    public Date expiration;

    /**
     * List of strings which are scopes for temporary credentials to give
     * to the worker through the secret system.  Scopes must be composed of
     * printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#/properties/scopes
     */
    public String[] scopes;

    /**
     * Free form object which contains the secrets stored
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#/properties/secrets
     */
    public Object secrets;

    /**
     * A Slug ID which is the uniquely addressable token to access this
     * set of secrets
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#/properties/token
     */
    public String token;

    /**
     * A string describing what the secret will be used for
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#/properties/workerType
     */
    public String workerType;
}
