// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/github/v1/api.json
package org.mozilla.taskcluster.client.github;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The github service, typically available at
 * `github.taskcluster.net`, is responsible for publishing pulse
 * messages in response to GitHub events.
 * 
 * This document describes the API end-point for consuming GitHub
 * web hooks
 *
 * @see "[Github API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs)"
 */
public class Github extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://github.taskcluster.net/v1";

    public Github(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Github(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Github(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Github(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Github(String baseURL) {
        super(baseURL);
    }

    public Github() {
        super(defaultBaseURL);
    }

    /**
     * Capture a GitHub event and publish it via pulse, if it's a push
     * or pull request.
     *
     * @see "[Consume GitHub WebHook API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#githubWebHookConsumer)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> githubWebHookConsumer() throws APICallFailure {
        return apiCall(null, "POST", "/github", EmptyPayload.class);
    }

    /**
     * A paginated list of builds that have been run in
     * Taskcluster. Can be filtered on various git-specific
     * fields.
     *
     * @see "[List of Builds API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#builds)"
     */
    public CallSummary<EmptyPayload, Builds> builds() throws APICallFailure {
        return apiCall(null, "GET", "/builds", Builds.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}