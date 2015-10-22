// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/purge-cache/v1/api.json
package org.mozilla.taskcluster.client.purgecache;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The purge-cache service, typically available at
 * `purge-cache.taskcluster.net`, is responsible for publishing a pulse
 * message for workers, so they can purge cache upon request.
 * 
 * This document describes the API end-point for publishing the pulse
 * message. This is mainly intended to be used by tools.
 *
 * See: http://docs.taskcluster.net/services/purge-cache
 */
public class PurgeCache extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://purge-cache.taskcluster.net/v1";

    public PurgeCache(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public PurgeCache(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public PurgeCache(String baseURL) {
        super(baseURL);
    }

    public PurgeCache() {
        super(defaultBaseURL);
    }

    /**
     * Publish a purge-cache message to purge caches named `cacheName` with
     * `provisionerId` and `workerType` in the routing-key. Workers should
     * be listening for this message and purge caches when they see it.
     *
     * See http://docs.taskcluster.net/services/purge-cache/#purgeCache
     */
    public CallSummary<PurgeCacheRequest, EmptyPayload> purgeCache(String provisionerId, String workerType, PurgeCacheRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/purge-cache/" + uriEncode(provisionerId) + "/" + uriEncode(workerType), EmptyPayload.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/services/purge-cache/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}