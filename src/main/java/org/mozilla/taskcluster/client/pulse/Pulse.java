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
 * Taskcluster credentials. This allows us self-service and
 * greater control within the Taskcluster project.
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
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * @see "[Ping Server API Documentation](https://docs.do.not.exist.yet.service.not.in.production#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }

    /**
     * An overview of the Rabbit cluster
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * @see "[Rabbit Overview API Documentation](https://docs.do.not.exist.yet.service.not.in.production#overview)"
     */
    public CallSummary<EmptyPayload, RabbitOverviewResponse> overview() throws APICallFailure {
        return apiCall(null, "GET", "/overview", RabbitOverviewResponse.class);
    }

    /**
     * Creates a namespace, given the taskcluster credentials with scopes.
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * Required scopes:
     *
     *   * `pulse:namespace:<namespace>`
     *
     * @see "[Create a namespace API Documentation](https://docs.do.not.exist.yet.service.not.in.production#namespace)"
     */
    public CallSummary<NamespaceCreationRequest, EmptyPayload> namespace(String namespace, NamespaceCreationRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/namespace/" + uriEncode(namespace), EmptyPayload.class);
    }
}