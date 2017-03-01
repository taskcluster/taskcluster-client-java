// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/auth/v1/api.json
package org.mozilla.taskcluster.client.auth;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
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
 * ### Guarded Services
 * The authentication service also has API end-points for delegating access
 * to some guarded service such as AWS S3, or Azure Table Storage.
 * Generally, we add API end-points to this server when we wish to use
 * TaskCluster credentials to grant access to a third-party service used
 * by many TaskCluster components.
 *
 * @see "[Auth API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs)"
 */
public class Auth extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://auth.taskcluster.net/v1";

    public Auth(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Auth(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Auth(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Auth(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
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
     * @see "[List Clients API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#listClients)"
     */
    public CallSummary<EmptyPayload, GetClientResponse[]> listClients() throws APICallFailure {
        return apiCall(null, "GET", "/clients/", GetClientResponse[].class);
    }

    /**
     * Get information about a single client.
     *
     * @see "[Get Client API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#client)"
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
     * Required scopes:
     *
     *   * `auth:create-client:<clientId>`
     *
     * @see "[Create Client API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#createClient)"
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
     * Required scopes:
     *
     *   * `auth:reset-access-token:<clientId>`
     *
     * @see "[Reset `accessToken` API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#resetAccessToken)"
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
     * Required scopes:
     *
     *   * `auth:update-client:<clientId>`
     *
     * @see "[Update Client API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#updateClient)"
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
     * Required scopes:
     *
     *   * `auth:enable-client:<clientId>`
     *
     * @see "[Enable Client API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#enableClient)"
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
     * Required scopes:
     *
     *   * `auth:disable-client:<clientId>`
     *
     * @see "[Disable Client API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#disableClient)"
     */
    public CallSummary<EmptyPayload, GetClientResponse> disableClient(String clientId) throws APICallFailure {
        return apiCall(null, "POST", "/clients/" + uriEncode(clientId) + "/disable", GetClientResponse.class);
    }

    /**
     * Delete a client, please note that any roles related to this client must
     * be deleted independently.
     *
     * Required scopes:
     *
     *   * `auth:delete-client:<clientId>`
     *
     * @see "[Delete Client API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#deleteClient)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> deleteClient(String clientId) throws APICallFailure {
        return apiCall(null, "DELETE", "/clients/" + uriEncode(clientId), EmptyPayload.class);
    }

    /**
     * Get a list of all roles, each role object also includes the list of
     * scopes it expands to.
     *
     * @see "[List Roles API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#listRoles)"
     */
    public CallSummary<EmptyPayload, GetRoleResponse[]> listRoles() throws APICallFailure {
        return apiCall(null, "GET", "/roles/", GetRoleResponse[].class);
    }

    /**
     * Get information about a single role, including the set of scopes that the
     * role expands to.
     *
     * @see "[Get Role API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#role)"
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
     * Required scopes:
     *
     *   * `auth:create-role:<roleId>`
     *
     * @see "[Create Role API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#createRole)"
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
     * Required scopes:
     *
     *   * `auth:update-role:<roleId>`
     *
     * @see "[Update Role API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#updateRole)"
     */
    public CallSummary<CreateRoleRequest, GetRoleResponse> updateRole(String roleId, CreateRoleRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/roles/" + uriEncode(roleId), GetRoleResponse.class);
    }

    /**
     * Delete a role. This operation will succeed regardless of whether or not
     * the role exists.
     *
     * Required scopes:
     *
     *   * `auth:delete-role:<roleId>`
     *
     * @see "[Delete Role API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#deleteRole)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> deleteRole(String roleId) throws APICallFailure {
        return apiCall(null, "DELETE", "/roles/" + uriEncode(roleId), EmptyPayload.class);
    }

    /**
     * Return an expanded copy of the given scopeset, with scopes implied by any
     * roles included.
     *
     * @see "[Expand Scopes API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#expandScopes)"
     */
    public CallSummary<SetOfScopes, SetOfScopes> expandScopes(SetOfScopes payload) throws APICallFailure {
        return apiCall(payload, "GET", "/scopes/expand", SetOfScopes.class);
    }

    /**
     * Return the expanded scopes available in the request, taking into account all sources
     * of scopes and scope restrictions (temporary credentials, assumeScopes, client scopes,
     * and roles).
     *
     * @see "[Get Current Scopes API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#currentScopes)"
     */
    public CallSummary<EmptyPayload, SetOfScopes> currentScopes() throws APICallFailure {
        return apiCall(null, "GET", "/scopes/current", SetOfScopes.class);
    }

    /**
     * Get temporary AWS credentials for `read-write` or `read-only` access to
     * a given `bucket` and `prefix` within that bucket.
     * The `level` parameter can be `read-write` or `read-only` and determines
     * which type of credentials are returned. Please note that the `level`
     * parameter is required in the scope guarding access.  The bucket name must
     * not contain `.`, as recommended by Amazon.
     * 
     * This method can only allow access to a whitelisted set of buckets.  To add
     * a bucket to that whitelist, contact the TaskCluster team, who will add it to
     * the appropriate IAM policy.  If the bucket is in a different AWS account, you
     * will also need to add a bucket policy allowing access from the TaskCluster
     * account.  That policy should look like this:
     * 
     * ```js
     * {
     *   "Version": "2012-10-17",
     *   "Statement": [
     *     {
     *       "Sid": "allow-taskcluster-auth-to-delegate-access",
     *       "Effect": "Allow",
     *       "Principal": {
     *         "AWS": "arn:aws:iam::692406183521:root"
     *       },
     *       "Action": [
     *         "s3:ListBucket",
     *         "s3:GetObject",
     *         "s3:PutObject",
     *         "s3:DeleteObject",
     *         "s3:GetBucketLocation"
     *       ],
     *       "Resource": [
     *         "arn:aws:s3:::<bucket>",
     *         "arn:aws:s3:::<bucket>/*"
     *       ]
     *     }
     *   ]
     * }
     * ```
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
     * Finally, note that the `PutObjectAcl` call is not allowed.  Passing a canned
     * ACL other than `private` to `PutObject` is treated as a `PutObjectAcl` call, and
     * will result in an access-denied error from AWS.  This limitation is due to a
     * security flaw in Amazon S3 which might otherwise allow indefinite access to
     * uploaded objects.
     * 
     * **EC2 metadata compatibility**, if the querystring parameter
     * `?format=iam-role-compat` is given, the response will be compatible
     * with the JSON exposed by the EC2 metadata service. This aims to ease
     * compatibility for libraries and tools built to auto-refresh credentials.
     * For details on the format returned by EC2 metadata service see:
     * [EC2 User Guide](http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/iam-roles-for-amazon-ec2.html#instance-metadata-security-credentials).
     *
     * Required scopes:
     *
     *   * `auth:aws-s3:<level>:<bucket>/<prefix>`
     *
     * @see "[Get Temporary Read/Write Credentials S3 API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#awsS3Credentials)"
     */
    public CallSummary<EmptyPayload, AWSS3CredentialsResponse> awsS3Credentials(String level, String bucket, String prefix) throws APICallFailure {
        return apiCall(null, "GET", "/aws/s3/" + uriEncode(level) + "/" + uriEncode(bucket) + "/" + uriEncode(prefix), AWSS3CredentialsResponse.class);
    }

    /**
     * Retrieve a list of all Azure accounts managed by Taskcluster Auth.
     *
     * Required scopes:
     *
     *   * `auth:azure-table:list-accounts`
     *
     * @see "[List Accounts Managed by Auth API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#azureAccounts)"
     */
    public CallSummary<EmptyPayload, AzureListAccountResponse> azureAccounts() throws APICallFailure {
        return apiCall(null, "GET", "/azure/accounts", AzureListAccountResponse.class);
    }

    /**
     * Retrieve a list of all tables in an account.
     *
     * Required scopes:
     *
     *   * `auth:azure-table:list-tables:<account>`
     *
     * @see "[List Tables in an Account Managed by Auth API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#azureTables)"
     */
    public CallSummary<EmptyPayload, AzureListAccountResponse1> azureTables(String account) throws APICallFailure {
        return apiCall(null, "GET", "/azure/" + uriEncode(account) + "/tables", AzureListAccountResponse1.class);
    }

    /**
     * Get a shared access signature (SAS) string for use with a specific Azure
     * Table Storage table. By not specifying a level as in azureTableSASLevel,
     * you will get read-write permissions. If you get read-write from this, it will create the
     * table if it doesn't already exist.
     *
     * Required scopes:
     *
     *   * `auth:azure-table:<level>:<account>/<table>`
     *
     * @see "[Get Shared-Access-Signature for Azure Table API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#azureTableSAS)"
     */
    public CallSummary<EmptyPayload, Var> azureTableSAS(String account, String table, String level) throws APICallFailure {
        return apiCall(null, "GET", "/azure/" + uriEncode(account) + "/table/" + uriEncode(table) + "/" + uriEncode(level), Var.class);
    }

    /**
     * Get a shared access signature (SAS) string for use with a specific Azure
     * Blob Storage container. If the level is read-write, the container will be created, if it doesn't already exists.
     *
     * Required scopes:
     *
     *   * `auth:azure-blob:<level>:<account>/<container>`
     *
     * @see "[Get Shared-Access-Signature for Azure Blob API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#azureBlobSAS)"
     */
    public CallSummary<EmptyPayload, Var1> azureBlobSAS(String account, String container, String level) throws APICallFailure {
        return apiCall(null, "GET", "/azure/" + uriEncode(account) + "/containers/" + uriEncode(container) + "/" + uriEncode(level), Var1.class);
    }

    /**
     * Get temporary DSN (access credentials) for a sentry project.
     * The credentials returned can be used with any Sentry client for up to
     * 24 hours, after which the credentials will be automatically disabled.
     * 
     * If the project doesn't exist it will be created, and assigned to the
     * initial team configured for this component. Contact a Sentry admin
     * to have the project transferred to a team you have access to if needed
     *
     * Required scopes:
     *
     *   * `auth:sentry:<project>`
     *
     * @see "[Get DSN for Sentry Project API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#sentryDSN)"
     */
    public CallSummary<EmptyPayload, SentryDSNResponse> sentryDSN(String project) throws APICallFailure {
        return apiCall(null, "GET", "/sentry/" + uriEncode(project) + "/dsn", SentryDSNResponse.class);
    }

    /**
     * Get temporary `token` and `baseUrl` for sending metrics to statsum.
     * 
     * The token is valid for 24 hours, clients should refresh after expiration.
     *
     * Required scopes:
     *
     *   * `auth:statsum:<project>`
     *
     * @see "[Get Token for Statsum Project API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#statsumToken)"
     */
    public CallSummary<EmptyPayload, StatsumTokenResponse> statsumToken(String project) throws APICallFailure {
        return apiCall(null, "GET", "/statsum/" + uriEncode(project) + "/token", StatsumTokenResponse.class);
    }

    /**
     * Validate the request signature given on input and return list of scopes
     * that the authenticating client has.
     * 
     * This method is used by other services that wish rely on TaskCluster
     * credentials for authentication. This way we can use Hawk without having
     * the secret credentials leave this service.
     *
     * @see "[Authenticate Hawk Request API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#authenticateHawk)"
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
     * @see "[Test Authentication API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#testAuthenticate)"
     */
    public CallSummary<TestAuthenticateRequest, TestAuthenticateResponse> testAuthenticate(TestAuthenticateRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/test-authenticate", TestAuthenticateResponse.class);
    }

    /**
     * Utility method similar to `testAuthenticate`, but with the GET method,
     * so it can be used with signed URLs (bewits).
     * 
     * Rather than using real credentials, this endpoint accepts requests with
     * clientId `tester` and accessToken `no-secret`. That client's scopes are
     * `['test:*', 'auth:create-client:test:*']`.  The call fails if the 
     * `test:authenticate-get` scope is not available.
     * 
     * The request is validated, with any certificate, authorizedScopes, etc.
     * applied, and the resulting scopes are checked, just like any API call.
     * On success, the response contains the clientId and scopes as seen by
     * the API method.
     * 
     * This method may later be extended to allow specification of client and
     * required scopes via query arguments.
     *
     * @see "[Test Authentication (GET) API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#testAuthenticateGet)"
     */
    public CallSummary<EmptyPayload, TestAuthenticateResponse> testAuthenticateGet() throws APICallFailure {
        return apiCall(null, "GET", "/test-authenticate-get/", TestAuthenticateResponse.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/platform/auth/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}