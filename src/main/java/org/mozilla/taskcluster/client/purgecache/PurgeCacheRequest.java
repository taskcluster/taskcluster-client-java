package org.mozilla.taskcluster.client.purgecache;

/**
 * Request that a message be published to purge a specific cache.
 *
 * See http://schemas.taskcluster.net/purge-cache/v1/purge-cache-request.json#
 */
public class PurgeCacheRequest {

	/**
	 * Name of cache to purge. Notice that if a `workerType` have multiple kinds
	 * of caches (with independent names), it should purge all caches identified
	 * by `cacheName` regardless of cache type.
	 */
	public String cacheName;
}
