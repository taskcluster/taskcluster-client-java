package org.mozilla.taskcluster.client.auth;

/**
 * A list of Azure tables in an account
 *
 * See http://schemas.taskcluster.net/auth/v1/azure-table-list-response.json#
 */
public class AzureListTableResponse {

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of tables.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called `azureAccountTables` with `continuationToken`
     * until you get a result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/auth/v1/azure-table-list-response.json#/properties/continuationToken
     */
    public String continuationToken;

    /**
     * A list of tables that are in an account. These are
     * the tables that can have SAS credentials fetched for them.
     *
     * See http://schemas.taskcluster.net/auth/v1/azure-table-list-response.json#/properties/tables
     */
    public String[] tables;
}
