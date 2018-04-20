package org.mozilla.taskcluster.client.purgecache;

/**
 * A list of currently open purge-cache requests.
 *
 * See http://schemas.taskcluster.net/purge-cache/v1/purge-cache-request-list.json#
 */
public class OpenPurgeRequestList {

    /**
     * True if the cache has been used in this request.
     *
     * See http://schemas.taskcluster.net/purge-cache/v1/purge-cache-request-list.json#/properties/cacheHit
     */
    public boolean cacheHit;

    /**
     * See http://schemas.taskcluster.net/purge-cache/v1/purge-cache-request-list.json#/properties/requests
     */
    public PurgeCacheRequestsEntry[] requests;
}
