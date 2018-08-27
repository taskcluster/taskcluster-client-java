package org.mozilla.taskcluster.client.auth;

/**
 * A list of Azure accounts managed by taskcluster-auth
 *
 * See https://schemas.taskcluster.net/auth/v1/azure-account-list-response.json#
 */
public class AzureListAccountResponse {

    /**
     * A list of accountIds that are managed by auth. These are
     * the accounts that can have SAS credentials fetched for tables
     * within them.
     *
     * See https://schemas.taskcluster.net/auth/v1/azure-account-list-response.json#/properties/accounts
     */
    public String[] accounts;
}
