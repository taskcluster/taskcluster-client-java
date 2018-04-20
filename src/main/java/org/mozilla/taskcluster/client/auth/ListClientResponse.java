package org.mozilla.taskcluster.client.auth;

/**
 * List of clients
 *
 * See http://schemas.taskcluster.net/auth/v1/list-clients-response.json#
 */
public class ListClientResponse {

    /**
     * See http://schemas.taskcluster.net/auth/v1/list-clients-response.json#/properties/clients
     */
    public GetClientResponse[] clients;

    /**
     * A continuation token is returned if there are more results than listed
     * here. You can optionally provide the token in the request payload to
     * load the additional results.
     *
     * See http://schemas.taskcluster.net/auth/v1/list-clients-response.json#/properties/continuationToken
     */
    public String continuationToken;
}
