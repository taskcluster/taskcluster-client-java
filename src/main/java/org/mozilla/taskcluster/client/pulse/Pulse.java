// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/pulse/v1/api.json
package org.mozilla.taskcluster.client.pulse;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The taskcluster-pulse service, typically available at `pulse.taskcluster.net`
 * manages pulse credentials for taskcluster users.
 * 
 * A service to manage Pulse credentials for anything using
 * Taskcluster credentials. This allows for self-service pulse
 * access and greater control within the Taskcluster project.
 *
 * @see "[Pulse API Documentation](https://docs.do.not.exist.yet.service.not.in.production)"
 */
public class Pulse extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://pulse.taskcluster.net/v1";

    public Pulse(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Pulse(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Pulse(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Pulse(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Pulse(String baseURL) {
        super(baseURL);
    }

    public Pulse() {
        super(defaultBaseURL);
    }

    /**
     * Get an overview of the Rabbit cluster.
     *
     * @see "[Rabbit Overview API Documentation](https://docs.do.not.exist.yet.service.not.in.production#overview)"
     */
    public CallSummary<EmptyPayload, RabbitOverviewResponse> overview() throws APICallFailure {
        return apiCall(null, "GET", "/overview", RabbitOverviewResponse.class);
    }

    /**
     * Get a list of all exchanges in the rabbit cluster.  This will include exchanges
     * not managed by this service, if any exist.
     *
     * @see "[Rabbit Exchanges API Documentation](https://docs.do.not.exist.yet.service.not.in.production#exchanges)"
     */
    public CallSummary<EmptyPayload, Exchange[]> exchanges() throws APICallFailure {
        return apiCall(null, "GET", "/exchanges", Exchange[].class);
    }

    /**
     * List the namespaces managed by this service.
     * 
     * This will list up to 1000 namespaces. If more namespaces are present a
     * `continuationToken` will be returned, which can be given in the next
     * request. For the initial request, do not provide continuation.
     *
     * @see "[List Namespaces API Documentation](https://docs.do.not.exist.yet.service.not.in.production#listNamespaces)"
     */
    public CallSummary<EmptyPayload, ListNamespacesResponse> listNamespaces() throws APICallFailure {
        return apiCall(null, "GET", "/namespaces", ListNamespacesResponse.class);
    }

    /**
     * Claim a namespace, returning a username and password with access to that
     * namespace good for a short time.  Clients should call this endpoint again
     * at the re-claim time given in the response, as the password will be rotated
     * soon after that time.  The namespace will expire, and any associated queues
     * and exchanges will be deleted, at the given expiration time.
     * 
     * The `expires` and `contact` properties can be updated at any time in a reclaim
     * operation.
     *
     * Required scopes:
     *
     *   * `pulse:namespace:<namespace>`
     *
     * @see "[Claim a namespace API Documentation](https://docs.do.not.exist.yet.service.not.in.production#claimNamespace)"
     */
    public CallSummary<NamespaceCreationRequest, NamespaceCreationResponse> claimNamespace(String namespace, NamespaceCreationRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/namespace/" + uriEncode(namespace), NamespaceCreationResponse.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.do.not.exist.yet.service.not.in.production#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}