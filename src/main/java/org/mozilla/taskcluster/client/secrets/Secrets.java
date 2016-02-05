// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/secrets/v1/api.json
package org.mozilla.taskcluster.client.secrets;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The secrets service, is a simple key/value store for secret data
 * guarded by TaskCluster scopes.  It is typically available at
 * `secrets.taskcluster.net`.
 *
 * See: http://docs.taskcluster.net/services/secrets
 */
public class Secrets extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://secrets.taskcluster.net/v1";

    public Secrets(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public Secrets(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public Secrets(String baseURL) {
        super(baseURL);
    }

    public Secrets() {
        super(defaultBaseURL);
    }

    /**
     * Set a secret associated with some key.  If the secret already exists, it is updated instead.
     *
     * See http://docs.taskcluster.net/services/secrets/#set
     */
    public CallSummary<Secret, EmptyPayload> set(String name, Secret payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/secret/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Delete the secret attached to some key.
     *
     * See http://docs.taskcluster.net/services/secrets/#remove
     */
    public CallSummary<EmptyPayload, EmptyPayload> remove(String name) throws APICallFailure {
        return apiCall(null, "DELETE", "/secret/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Read the secret attached to some key.
     *
     * See http://docs.taskcluster.net/services/secrets/#get
     */
    public CallSummary<EmptyPayload, Secret> get(String name) throws APICallFailure {
        return apiCall(null, "GET", "/secret/" + uriEncode(name), Secret.class);
    }

    /**
     * List the names of all visible secrets.
     *
     * See http://docs.taskcluster.net/services/secrets/#list
     */
    public CallSummary<EmptyPayload, SecretsList> list() throws APICallFailure {
        return apiCall(null, "GET", "/secrets", SecretsList.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/services/secrets/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}