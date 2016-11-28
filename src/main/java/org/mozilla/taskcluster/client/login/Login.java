// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/login/v1/api.json
package org.mozilla.taskcluster.client.login;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The Login service serves as the interface between external authentication
 * systems and TaskCluster credentials.  It acts as the server side of
 * https://tools.taskcluster.net.  If you are working on federating logins
 * with TaskCluster, this is probably *not* the service you are looking for.
 * Instead, use the federated login support in the tools site.
 *
 * @see "[Login API Documentation](https://docs.taskcluster.net/reference/core/login/api-docs)"
 */
public class Login extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://login.taskcluster.net/v1";

    public Login(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Login(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Login(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Login(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Login(String baseURL) {
        super(baseURL);
    }

    public Login() {
        super(defaultBaseURL);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/login/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}