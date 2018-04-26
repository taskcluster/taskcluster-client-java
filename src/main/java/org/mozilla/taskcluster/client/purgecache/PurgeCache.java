// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// https://references.taskcluster.net/purge-cache/v1/api.json
package org.mozilla.taskcluster.client.purgecache;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

/**
 * The purge-cache service, typically available at
 * `purge-cache.taskcluster.net`, is responsible for publishing a pulse
 * message for workers, so they can purge cache upon request.
 * 
 * This document describes the API end-point for publishing the pulse
 * message. This is mainly intended to be used by tools.
 *
 * @see "[PurgeCache API Documentation](https://docs.taskcluster.net/reference/core/purge-cache/api-docs)"
 */
public class PurgeCache extends TaskclusterRequestHandler {

    protected static final String defaultBaseURL = "https://purge-cache.taskcluster.net/v1";

    public PurgeCache(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public PurgeCache(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public PurgeCache(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public PurgeCache(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
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

     * Required scopes:
     *   purge-cache:<provisionerId>/<workerType>:<cacheName>
     *
     * @see "[Purge Worker Cache API Documentation](https://docs.taskcluster.net/reference/core/purge-cache/api-docs#purgeCache)"
     */
    public CallSummary<PurgeCacheRequest, EmptyPayload> purgeCache(String provisionerId, String workerType, PurgeCacheRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/purge-cache/" + uriEncode(provisionerId) + "/" + uriEncode(workerType), EmptyPayload.class);
    }

    /**
     * This is useful mostly for administors to view
     * the set of open purge requests. It should not
     * be used by workers. They should use the purgeRequests
     * endpoint that is specific to their workerType and
     * provisionerId.
     *
     * @see "[All Open Purge Requests API Documentation](https://docs.taskcluster.net/reference/core/purge-cache/api-docs#allPurgeRequests)"
     */
    public CallSummary<EmptyPayload, OpenAllPurgeRequestsList> allPurgeRequests() throws APICallFailure {
        return apiCall(null, "GET", "/purge-cache/list", OpenAllPurgeRequestsList.class);
    }

    /**
     * List of caches that need to be purged if they are from before
     * a certain time. This is safe to be used in automation from
     * workers.
     *
     * @see "[Open Purge Requests for a provisionerId/workerType pair API Documentation](https://docs.taskcluster.net/reference/core/purge-cache/api-docs#purgeRequests)"
     */
    public CallSummary<EmptyPayload, OpenPurgeRequestList> purgeRequests(String provisionerId, String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/purge-cache/" + uriEncode(provisionerId) + "/" + uriEncode(workerType), OpenPurgeRequestList.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/purge-cache/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}