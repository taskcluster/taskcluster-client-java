package org.mozilla.taskcluster.client.purgecache;

import java.util.Date;

/**
 * An entry in a list of Purge Cache Requests that the Purge Cache service has previously received.
 *
 * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-requests.json#/items
 */
public class PurgeCacheRequestsEntry {

    /**
     * All caches that match this provisionerId, workerType, and cacheName must be destroyed if they were created _before_ this time.
     *
     * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-requests.json#/items/properties/before
     */
    public Date before;

    /**
     * Name of cache to purge.
     *
     * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-requests.json#/items/properties/cacheName
     */
    public String cacheName;

    /**
     * ProvisionerId associated with the workerType.
     *
     * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-requests.json#/items/properties/provisionerId
     */
    public String provisionerId;

    /**
     * Workertype cache exists on.
     *
     * See https://schemas.taskcluster.net/purge-cache/v1/purge-cache-requests.json#/items/properties/workerType
     */
    public String workerType;
}
