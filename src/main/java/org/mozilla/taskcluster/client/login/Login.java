// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/login/v1/api.json
package org.mozilla.taskcluster.client.login;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

/**
 * The Login service serves as the interface between external authentication
 * systems and TaskCluster credentials.
 *
 * @see "[Login API Documentation](https://docs.taskcluster.net/reference/core/login/api-docs)"
 */
public class Login extends TaskclusterRequestHandler {

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
     * Given an OIDC `access_token` from a trusted OpenID provider, return a
     * set of Taskcluster credentials for use on behalf of the identified
     * user.
     * 
     * This method is typically not called with a Taskcluster client library
     * and does not accept Hawk credentials. The `access_token` should be
     * given in an `Authorization` header:
     * ```
     * Authorization: Bearer abc.xyz
     * ```
     * 
     * The `access_token` is first verified against the named
     * :provider, then passed to the provider's API to retrieve a user
     * profile. That profile is then used to generate Taskcluster credentials
     * appropriate to the user. Note that the resulting credentials may or may
     * not include a `certificate` property. Callers should be prepared for either
     * alternative.
     * 
     * The given credentials will expire in a relatively short time. Callers should
     * monitor this expiration and refresh the credentials if necessary, by calling
     * this endpoint again, if they have expired.
     *
     * @see "[Get TaskCluster credentials given a suitable `access_token` API Documentation](https://docs.taskcluster.net/reference/core/login/api-docs#oidcCredentials)"
     */
    public CallSummary<EmptyPayload, CredentialsResponse> oidcCredentials(String provider) throws APICallFailure {
        return apiCall(null, "GET", "/oidc-credentials/" + uriEncode(provider), CredentialsResponse.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/login/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}