// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/secrets/v1/api.json
package org.mozilla.taskcluster.client.secrets;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

/**
 * The secrets service provides a simple key/value store for small bits of secret
 * data.  Access is limited by scopes, so values can be considered secret from
 * those who do not have the relevant scopes.
 * 
 * Secrets also have an expiration date, and once a secret has expired it can no
 * longer be read.  This is useful for short-term secrets such as a temporary
 * service credential or a one-time signing key.
 *
 * @see "[Secrets API Documentation](https://docs.taskcluster.net/reference/core/secrets/api-docs)"
 */
public class Secrets extends TaskclusterRequestHandler {

    protected static final String defaultBaseURL = "https://secrets.taskcluster.net/v1";

    public Secrets(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Secrets(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Secrets(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Secrets(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Secrets(String baseURL) {
        super(baseURL);
    }

    public Secrets() {
        super(defaultBaseURL);
    }

    /**
     * Set the secret associated with some key.  If the secret already exists, it is
     * updated instead.

     * Required scopes:
     *   secrets:set:<name>
     *
     * @see "[Set Secret API Documentation](https://docs.taskcluster.net/reference/core/secrets/api-docs#set)"
     */
    public CallSummary<Secret, EmptyPayload> set(String name, Secret payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/secret/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Delete the secret associated with some key.

     * Required scopes:
     *   secrets:set:<name>
     *
     * @see "[Delete Secret API Documentation](https://docs.taskcluster.net/reference/core/secrets/api-docs#remove)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> remove(String name) throws APICallFailure {
        return apiCall(null, "DELETE", "/secret/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Read the secret associated with some key.  If the secret has recently
     * expired, the response code 410 is returned.  If the caller lacks the
     * scope necessary to get the secret, the call will fail with a 403 code
     * regardless of whether the secret exists.

     * Required scopes:
     *   secrets:get:<name>
     *
     * @see "[Read Secret API Documentation](https://docs.taskcluster.net/reference/core/secrets/api-docs#get)"
     */
    public CallSummary<EmptyPayload, Secret> get(String name) throws APICallFailure {
        return apiCall(null, "GET", "/secret/" + uriEncode(name), Secret.class);
    }

    /**
     * List the names of all secrets.
     * 
     * By default this end-point will try to return up to 1000 secret names in one
     * request. But it **may return less**, even if more tasks are available.
     * It may also return a `continuationToken` even though there are no more
     * results. However, you can only be sure to have seen all results if you
     * keep calling `listTaskGroup` with the last `continuationToken` until you
     * get a result without a `continuationToken`.
     * 
     * If you are not interested in listing all the members at once, you may
     * use the query-string option `limit` to return fewer.
     *
     * @see "[List Secrets API Documentation](https://docs.taskcluster.net/reference/core/secrets/api-docs#list)"
     */
    public CallSummary<EmptyPayload, SecretsList> list() throws APICallFailure {
        return apiCall(null, "GET", "/secrets", SecretsList.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/secrets/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}