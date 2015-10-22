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
 * The secrets service, typically available at
 * `tools.taskcluster.net`, is responsible for managing
 * secure data in TaskCluster.
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
     * Set a secret associated with some key.
     *
     * See http://docs.taskcluster.net/services/secrets/#set
     */
    public CallSummary<ATaskClusterSecret, EmptyPayload> set(String name, ATaskClusterSecret payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/secrets/" + name + "", EmptyPayload.class);
    }

    /**
     * Update a secret associated with some key.
     *
     * See http://docs.taskcluster.net/services/secrets/#update
     */
    public CallSummary<ATaskClusterSecret, EmptyPayload> update(String name, ATaskClusterSecret payload) throws APICallFailure {
        return apiCall(payload, "POST", "/secrets/" + name + "", EmptyPayload.class);
    }

    /**
     * Delete the secret attached to some key.
     *
     * See http://docs.taskcluster.net/services/secrets/#remove
     */
    public CallSummary<EmptyPayload, EmptyPayload> remove(String name) throws APICallFailure {
        return apiCall(null, "DELETE", "/secrets/" + name + "", EmptyPayload.class);
    }

    /**
     * Read the secret attached to some key.
     *
     * See http://docs.taskcluster.net/services/secrets/#get
     */
    public CallSummary<EmptyPayload, ATaskClusterSecret> get(String name) throws APICallFailure {
        return apiCall(null, "GET", "/secrets/" + name + "", ATaskClusterSecret.class);
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