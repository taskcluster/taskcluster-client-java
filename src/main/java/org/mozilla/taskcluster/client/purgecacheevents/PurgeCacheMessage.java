package org.mozilla.taskcluster.client.purgecacheevents;

/**
* Message reporting that a specific cache should be purged
*
* See http://schemas.taskcluster.net/purge-cache/v1/purge-cache-message.json#
*/
public class PurgeCacheMessage {

    /**
     * Name of cache to purge. Notice that if a `workerType` have multiple kinds
     * of caches (with independent names), it should purge all caches identified
     * by `cacheName` regardless of cache type.
     */
    public String cacheName;

    /**
     * `provisionerId` under which the `workerType` we want to purge for exists.
     */
    public String provisionerId;

    /**
     * Message version
     */
    public int version;

    /**
     * `workerType` we wish to purge cache for.
     */
    public String workerType;
}