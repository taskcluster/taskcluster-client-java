package org.mozilla.taskcluster.client.purgecache;

/**
 * A list of currently open purge-cache requests.
 *
 * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-request-list.json#
 */
public class OpenPurgeRequestList {

    /**
     * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-request-list.json#/properties/requests
     */
    public PurgeCacheRequestsEntry[] requests;
}
