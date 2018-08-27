package org.mozilla.taskcluster.client.auth;

/**
 * A list of Azure containers in an account
 *
 * See https://schemas.taskcluster.net/auth/v1/azure-container-list-response.json#
 */
public class AzureListContainersResponse {

    /**
     * A list of containers that are in an account.  Credentials are available for
     * these containers from the `azureBlobSAS` method.
     *
     * See https://schemas.taskcluster.net/auth/v1/azure-container-list-response.json#/properties/containers
     */
    public String[] containers;

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of containers.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called this method with `continuationToken`
     * until you get a result without a `continuationToken`.
     *
     * See https://schemas.taskcluster.net/auth/v1/azure-container-list-response.json#/properties/continuationToken
     */
    public String continuationToken;
}
