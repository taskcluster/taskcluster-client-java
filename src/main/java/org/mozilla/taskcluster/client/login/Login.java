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
 * The API methods described here issue temporary credentials based on
 * an assertion.  The assertion identifies the user, usually with an
 * email-like string.  This string is then passed through a series of
 * authorizers, each of which may supply scopes to be included in the
 * credentials. Finally, the service generates temporary credentials based
 * on those scopes.
 * 
 * The generated credentials include scopes to create new, permanent clients
 * with names based on the user's identifier.  These credentials are
 * periodically scanned for scopes that the user does not posess, and disabled
 * if such scopes are discovered.  Thus users can create long-lived credentials
 * that are only usable until the user's access level is reduced.
 *
 * See: https://docs.taskcluster.net/reference/core/login/api-docs
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
     * Given an [assertion](https://developer.mozilla.org/en-US/Persona/Quick_setup#Step_4_Verify_the_user%E2%80%99s_credentials), return
     * an appropriate set of temporary credentials.
     *
     * See https://docs.taskcluster.net/reference/core/login/api-docs/#credentialsFromPersonaAssertion
     */
    public CallSummary<EmptyPayload, CredentialsResponse> credentialsFromPersonaAssertion() throws APICallFailure {
        return apiCall(null, "POST", "/persona", CredentialsResponse.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See https://docs.taskcluster.net/reference/core/login/api-docs/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}