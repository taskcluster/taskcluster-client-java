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
 * Authentication related API end-points for taskcluster.
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

    /**
     * Returns the scopes the client is authorized to access and the date-time
     * when the clients authorization is set to expire.
     * 
     * This API end-point allows you inspect clients without getting access to
     * credentials, as provided by the `getCredentials` request below.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#scopes
     */
    public CallSummary<EmptyPayload,GetClientScopesResponse> scopes(String clientId) throws APICallFailure {
        return apiCall(null, "GET", "/client/" + clientId + "/scopes", GetClientScopesResponse.class);
    }

    /**
     * Returns the client's `accessToken` as needed for verifying signatures.
     * This API end-point also returns the list of scopes the client is
     * authorized for and the date-time where the client authorization expires
     * 
     * Remark, **if you don't need** the `accessToken` but only want to see what
     * scopes a client is authorized for, you should use the `getScopes`
     * function described above.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#getCredentials
     */
    public CallSummary<EmptyPayload,GetClientCredentialsResponse> getCredentials(String clientId) throws APICallFailure {
        return apiCall(null, "GET", "/client/" + clientId + "/credentials", GetClientCredentialsResponse.class);
    }

    /**
     * Returns all information about a given client. This end-point is mostly for
     * building tools to administrate clients. Do not use if you only want to
     * authenticate a request; see `getCredentials` for this purpose.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#client
     */
    public CallSummary<EmptyPayload,GetClientResponse> client(String clientId) throws APICallFailure {
        return apiCall(null, "GET", "/client/" + clientId + "", GetClientResponse.class);
    }

    /**
     * Create a client with given `clientId`, `name`, `expires`, `scopes` and
     * `description`. The `accessToken` will always be generated server-side,
     * and will be returned from this request.
     * 
     * **Required scopes**: in addition the scopes listed above, the 
     * `scopes` property must be satisfied by the caller's scopes.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#createClient
     */
    public CallSummary<GetClientCredentialsResponse1,GetClientResponse> createClient(String clientId, GetClientCredentialsResponse1 payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/client/" + clientId + "", GetClientResponse.class);
    }

    /**
     * Modify client `name`, `expires`, `scopes` and
     * `description`.
     * 
     * **Required scopes**: in addition the scopes listed
     * above, the `scopes` property must be satisfied by the caller's
     * scopes.  The client's existing scopes are not considered.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#modifyClient
     */
    public CallSummary<GetClientCredentialsResponse1,GetClientResponse> modifyClient(String clientId, GetClientCredentialsResponse1 payload) throws APICallFailure {
        return apiCall(payload, "POST", "/client/" + clientId + "/modify", GetClientResponse.class);
    }

    /**
     * Delete a client with given `clientId`.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#removeClient
     */
    public CallSummary<EmptyPayload,EmptyPayload> removeClient(String clientId) throws APICallFailure {
        return apiCall(null, "DELETE", "/client/" + clientId + "", EmptyPayload.class);
    }

    /**
     * Reset credentials for a client. This will generate a new `accessToken`.
     * As always, the `accessToken` will be generated server-side and returned.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#resetCredentials
     */
    public CallSummary<EmptyPayload,GetClientResponse> resetCredentials(String clientId) throws APICallFailure {
        return apiCall(null, "POST", "/client/" + clientId + "/reset-credentials", GetClientResponse.class);
    }

    /**
     * Return a list of all clients, not including their access tokens.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#listClients
     */
    public CallSummary<EmptyPayload,ListClientsResponse[]> listClients() throws APICallFailure {
        return apiCall(null, "GET", "/list-clients", ListClientsResponse[].class);
    }

    /**
     * Get a shared access signature (SAS) string for use with a specific Azure
     * Table Storage table.  Note, this will create the table, if it doesn't
     * already exist.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#azureTableSAS
     */
    public CallSummary<EmptyPayload,AzureSharedAccessSignatureResponse> azureTableSAS(String account, String table) throws APICallFailure {
        return apiCall(null, "GET", "/azure/" + account + "/table/" + table + "/read-write", AzureSharedAccessSignatureResponse.class);
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
    public CallSummary<EmptyPayload,AWSS3CredentialsResponse> awsS3Credentials(String level, String bucket, String prefix) throws APICallFailure {
        return apiCall(null, "GET", "/aws/s3/" + level + "/" + bucket + "/" + prefix + "", AWSS3CredentialsResponse.class);
    }

    /**
     * Export all clients except the root client, as a JSON list.
     * This list can be imported later using `importClients`.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#exportClients
     */
    public CallSummary<EmptyPayload,ExportedClients[]> exportClients() throws APICallFailure {
        return apiCall(null, "GET", "/export-clients", ExportedClients[].class);
    }

    /**
     * Import client from JSON list, overwriting any clients that already
     * exists. Returns a list of all clients imported.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#importClients
     */
    public CallSummary<ExportedClients,ExportedClients[]> importClients(ExportedClients payload) throws APICallFailure {
        return apiCall(payload, "POST", "/import-clients", ExportedClients[].class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/auth/api-docs/#ping
     */
    public CallSummary<EmptyPayload,EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}