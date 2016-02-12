// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/auth/v1/api.json
package org.mozilla.taskcluster.client.auth;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * Authentication related API end-points for TaskCluster and related
 * services. These API end-points are of interest if you wish to:
 *   * Authenticate request signed with TaskCluster credentials,
 *   * Manage clients and roles,
 *   * Inspect or audit clients and roles,
 *   * Gain access to various services guarded by this API.
 * 
 * ### Clients
 * The authentication service manages _clients_, at a high-level each client
 * consists of a `clientId`, an `accessToken`, scopes, and some metadata.
 * The `clientId` and `accessToken` can be used for authentication when
 * calling TaskCluster APIs.
 * 
 * The client's scopes control the client's access to TaskCluster resources.
 * The scopes are *expanded* by substituting roles, as defined below.
 * Every client has an implicit scope named `assume:client-id:<clientId>`,
 * allowing additional access to be granted to the client without directly
 * editing the client's scopes.
 * 
 * ### Roles
 * A _role_ consists of a `roleId`, a set of scopes and a description.
 * Each role constitutes a simple _expansion rule_ that says if you have
 * the scope: `assume:<roleId>` you get the set of scopes the role has.
 * Think of the `assume:<roleId>` as a scope that allows a client to assume
 * a role.
 * 
 * As in scopes the `*` kleene star also have special meaning if it is
 * located at the end of a `roleId`. If you have a role with the following
 * `roleId`: `my-prefix*`, then any client which has a scope staring with
 * `assume:my-prefix` will be allowed to assume the role.
 * 
 * As previously mentioned each client gets the scope:
 * `assume:client-id:<clientId>`, it trivially follows that you can create a
 * role with the `roleId`: `client-id:<clientId>` to assign additional
 * scopes to a client. You can also create a role `client-id:user-*`
 * if you wish to assign a set of scopes to all clients whose `clientId`
 * starts with `user-`.
 * 
 * ### Guarded Services
 * The authentication service also has API end-points for delegating access
 * to some guarded service such as AWS S3, or Azure Table Storage.
 * Generally, we add API end-points to this server when we wish to use
 * TaskCluster credentials to grant access to a third-party service used
 * by many TaskCluster components.
 *
 * See: http://docs.taskcluster.net/auth/api-docs
 */
public class Auth extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://auth.taskcluster.net/v1";

    public Auth(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public Auth(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public Auth(String baseURL) {
        super(baseURL);
    }

    public Auth() {
        super(defaultBaseURL);
    }

    /**
     * Get a list of all clients.  With `prefix`, only clients for which
     * it is a prefix of the clientId are returned.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#listClients
     */
    public CallSummary<EmptyPayload, GetClientResponse[]> listClients() throws APICallFailure {
        return apiCall(null, "GET", "/clients/", GetClientResponse[].class);
    }

    /**
     * Get information about a single client.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#client
     */
    public CallSummary<EmptyPayload, GetClientResponse> client(String clientId) throws APICallFailure {
        return apiCall(null, "GET", "/clients/" + uriEncode(clientId), GetClientResponse.class);
    }

    /**
     * Create a new client and get the `accessToken` for this client.
     * You should store the `accessToken` from this API call as there is no
     * other way to retrieve it.
     * 
     * If you loose the `accessToken` you can call `resetAccessToken` to reset
     * it, and a new `accessToken` will be returned, but you cannot retrieve the
     * current `accessToken`.
     * 
     * If a client with the same `clientId` already exists this operation will
     * fail. Use `updateClient` if you wish to update an existing client.
     * 
     * The caller's scopes must satisfy `scopes`.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#createClient
     */
    public CallSummary<CreateClientRequest, CreateClientResponse> createClient(String clientId, CreateClientRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/clients/" + uriEncode(clientId), CreateClientResponse.class);
    }

    /**
     * Reset a clients `accessToken`, this will revoke the existing
     * `accessToken`, generate a new `accessToken` and return it from this
     * call.
     * 
     * There is no way to retrieve an existing `accessToken`, so if you loose it
     * you must reset the accessToken to acquire it again.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#resetAccessToken
     */
    public CallSummary<EmptyPayload, CreateClientResponse> resetAccessToken(String clientId) throws APICallFailure {
        return apiCall(null, "POST", "/clients/" + uriEncode(clientId) + "/reset", CreateClientResponse.class);
    }

    /**
     * Update an exisiting client. The `clientId` and `accessToken` cannot be
     * updated, but `scopes` can be modified.  The caller's scopes must
     * satisfy all scopes being added to the client in the update operation.
     * If no scopes are given in the request, the client's scopes remain
     * unchanged
     *
     * See http://docs.taskcluster.net/auth/api-docs/#updateClient
     */
    public CallSummary<CreateClientRequest, GetClientResponse> updateClient(String clientId, CreateClientRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/clients/" + uriEncode(clientId), GetClientResponse.class);
    }

    /**
     * Enable a client that was disabled with `disableClient`.  If the client
     * is already enabled, this does nothing.
     * 
     * This is typically used by identity providers to re-enable clients that
     * had been disabled when the corresponding identity's scopes changed.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#enableClient
     */
    public CallSummary<EmptyPayload, GetClientResponse> enableClient(String clientId) throws APICallFailure {
        return apiCall(null, "POST", "/clients/" + uriEncode(clientId) + "/enable", GetClientResponse.class);
    }

    /**
     * Disable a client.  If the client is already disabled, this does nothing.
     * 
     * This is typically used by identity providers to disable clients when the
     * corresponding identity's scopes no longer satisfy the client's scopes.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#disableClient
     */
    public CallSummary<EmptyPayload, GetClientResponse> disableClient(String clientId) throws APICallFailure {
        return apiCall(null, "POST", "/clients/" + uriEncode(clientId) + "/disable", GetClientResponse.class);
    }

    /**
     * Delete a client, please note that any roles related to this client must
     * be deleted independently.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#deleteClient
     */
    public CallSummary<EmptyPayload, EmptyPayload> deleteClient(String clientId) throws APICallFailure {
        return apiCall(null, "DELETE", "/clients/" + uriEncode(clientId), EmptyPayload.class);
    }

    /**
     * Get a list of all roles, each role object also includes the list of
     * scopes it expands to.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#listRoles
     */
    public CallSummary<EmptyPayload, GetRoleResponse[]> listRoles() throws APICallFailure {
        return apiCall(null, "GET", "/roles/", GetRoleResponse[].class);
    }

    /**
     * Get information about a single role, including the set of scopes that the
     * role expands to.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#role
     */
    public CallSummary<EmptyPayload, GetRoleResponse> role(String roleId) throws APICallFailure {
        return apiCall(null, "GET", "/roles/" + uriEncode(roleId), GetRoleResponse.class);
    }

    /**
     * Create a new role.
     * 
     * The caller's scopes must satisfy the new role's scopes.
     * 
     * If there already exists a role with the same `roleId` this operation
     * will fail. Use `updateRole` to modify an existing role.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#createRole
     */
    public CallSummary<CreateRoleRequest, GetRoleResponse> createRole(String roleId, CreateRoleRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/roles/" + uriEncode(roleId), GetRoleResponse.class);
    }

    /**
     * Update an existing role.
     * 
     * The caller's scopes must satisfy all of the new scopes being added, but
     * need not satisfy all of the client's existing scopes.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#updateRole
     */
    public CallSummary<CreateRoleRequest, GetRoleResponse> updateRole(String roleId, CreateRoleRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/roles/" + uriEncode(roleId), GetRoleResponse.class);
    }

    /**
     * Delete a role. This operation will succeed regardless of whether or not
     * the role exists.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#deleteRole
     */
    public CallSummary<EmptyPayload, EmptyPayload> deleteRole(String roleId) throws APICallFailure {
        return apiCall(null, "DELETE", "/roles/" + uriEncode(roleId), EmptyPayload.class);
    }

    /**
     * Return an expanded copy of the given scopeset, with scopes implied by any
     * roles included.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#expandScopes
     */
    public CallSummary<SetOfScopes, SetOfScopes> expandScopes(SetOfScopes payload) throws APICallFailure {
        return apiCall(payload, "GET", "/scopes/expand", SetOfScopes.class);
    }

    /**
     * Return the expanded scopes available in the request, taking into account all sources
     * of scopes and scope restrictions (temporary credentials, assumeScopes, client scopes,
     * and roles).
     *
     * See http://docs.taskcluster.net/auth/api-docs/#currentScopes
     */
    public CallSummary<EmptyPayload, SetOfScopes> currentScopes() throws APICallFailure {
        return apiCall(null, "GET", "/scopes/current", SetOfScopes.class);
    }

    /**
     * Get temporary AWS credentials for `read-write` or `read-only` access to
     * a given `bucket` and `prefix` within that bucket.
     * The `level` parameter can be `read-write` or `read-only` and determines
     * which type of credentials are returned. Please note that the `level`
     * parameter is required in the scope guarding access.
     * 
     * The credentials are set to expire after an hour, but this behavior is
     * subject to change. Hence, you should always read the `expires` property
     * from the response, if you intend to maintain active credentials in your
     * application.
     * 
     * Please note that your `prefix` may not start with slash `/`. Such a prefix
     * is allowed on S3, but we forbid it here to discourage bad behavior.
     * 
     * Also note that if your `prefix` doesn't end in a slash `/`, the STS
     * credentials may allow access to unexpected keys, as S3 does not treat
     * slashes specially.  For example, a prefix of `my-folder` will allow
     * access to `my-folder/file.txt` as expected, but also to `my-folder.txt`,
     * which may not be intended.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#awsS3Credentials
     */
    public CallSummary<EmptyPayload, AWSS3CredentialsResponse> awsS3Credentials(String level, String bucket, String prefix) throws APICallFailure {
        return apiCall(null, "GET", "/aws/s3/" + uriEncode(level) + "/" + uriEncode(bucket) + "/" + uriEncode(prefix), AWSS3CredentialsResponse.class);
    }

    /**
     * Get a shared access signature (SAS) string for use with a specific Azure
     * Table Storage table.  Note, this will create the table, if it doesn't
     * already exist.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#azureTableSAS
     */
    public CallSummary<EmptyPayload, AzureSharedAccessSignatureResponse> azureTableSAS(String account, String table) throws APICallFailure {
        return apiCall(null, "GET", "/azure/" + uriEncode(account) + "/table/" + uriEncode(table) + "/read-write", AzureSharedAccessSignatureResponse.class);
    }

    /**
     * Validate the request signature given on input and return list of scopes
     * that the authenticating client has.
     * 
     * This method is used by other services that wish rely on TaskCluster
     * credentials for authentication. This way we can use Hawk without having
     * the secret credentials leave this service.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#authenticateHawk
     */
    public CallSummary<HawkSignatureAuthenticationRequest, Object> authenticateHawk(HawkSignatureAuthenticationRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/authenticate-hawk", Object.class);
    }

    /**
     * Utility method to test client implementations of TaskCluster
     * authentication.
     * 
     * Rather than using real credentials, this endpoint accepts requests with
     * clientId `tester` and accessToken `no-secret`. That client's scopes are
     * based on `clientScopes` in the request body.
     * 
     * The request is validated, with any certificate, authorizedScopes, etc.
     * applied, and the resulting scopes are checked against `requiredScopes`
     * from the request body. On success, the response contains the clientId
     * and scopes as seen by the API method.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#testAuthenticate
     */
    public CallSummary<TestAuthenticateRequest, TestAuthenticateResponse> testAuthenticate(TestAuthenticateRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/test-authenticate", TestAuthenticateResponse.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}