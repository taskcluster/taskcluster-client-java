package org.mozilla.taskcluster.client.purgecache;

/**
 * A list of currently open purge-cache requests. Should not be used by workers.
 *
 * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#
 */
public class OpenAllPurgeRequestsList {

    /**
     * Passed back from Azure to allow us to page through long result sets.
     *
     * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/continuationToken
     */
    public String continuationToken;

    /**
     * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/requests
     */
    public PurgeCacheRequestsEntry[] requests;
}
