package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
* A Secret
*
* See http://schemas.taskcluster.net/aws-provisioner/v1/create-secret-request.json#
*/
public class GetSecretRequest {

    /**
     * The date at which the secret is no longer guarunteed to exist
     */
    public Date expiration;

    /**
     * List of strings which are scopes for temporary credentials to give
     * to the worker through the secret system
     */
    public String[] scopes;

    /**
     * Free form object which contains the secrets stored
     */
    public Object secrets;

    /**
     * A Slug ID which is the uniquely addressable token to access this
     * set of secrets
     */
    public String token;

    /**
     * A string describing what the secret will be used for
     */
    public String workerType;
}