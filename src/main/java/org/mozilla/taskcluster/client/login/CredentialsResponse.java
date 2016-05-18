package org.mozilla.taskcluster.client.login;

/**
* A response containing temporary credentials.
*
* See http://schemas.taskcluster.net/login/v1/credentials-response.json#
*/
public class CredentialsResponse {
    public String accessToken;
    public String certificate;
    public String clientId;
}