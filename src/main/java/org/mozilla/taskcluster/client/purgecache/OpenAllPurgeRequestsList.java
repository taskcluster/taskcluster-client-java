package org.mozilla.taskcluster.client.purgecache;

import java.util.Date;

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

    public class RequestsEntry {

        /**
         * All caches that match this provisionerId, workerType, and cacheName must be destroyed if they were created _before_ this time.
         *
         * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/requests/items/properties/before
         */
        public Date before;

        /**
         * Name of cache to purge.
         *
         * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/requests/items/properties/cacheName
         */
        public String cacheName;

        /**
         * ProvisionerId associated with the workerType.
         *
         * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/requests/items/properties/provisionerId
         */
        public String provisionerId;

        /**
         * Workertype cache exists on.
         *
         * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/requests/items/properties/workerType
         */
        public String workerType;
    }

    /**
     * A simple list of purge-cache requests.
     *
     * See http://schemas.taskcluster.net/purge-cache/v1/all-purge-cache-request-list.json#/properties/requests
     */
    public RequestsEntry[] requests;
}
