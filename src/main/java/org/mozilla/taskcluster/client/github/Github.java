// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// https://references.taskcluster.net/github/v1/api.json
package org.mozilla.taskcluster.client.github;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

/**
 * The github service, typically available at
 * `github.taskcluster.net`, is responsible for publishing pulse
 * messages in response to GitHub events.
 * 
 * This document describes the API end-point for consuming GitHub
 * web hooks, as well as some useful consumer APIs.
 * 
 * When Github forbids an action, this service returns an HTTP 403
 * with code ForbiddenByGithub.
 *
 * @see "[Github API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs)"
 */
public class Github extends TaskclusterRequestHandler {

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
     * Capture a GitHub event and publish it via pulse, if it's a push,
     * release or pull request.
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
    public CallSummary<EmptyPayload, BuildsResponse> builds() throws APICallFailure {
        return apiCall(null, "GET", "/builds", BuildsResponse.class);
    }

    /**
     * Checks the status of the latest build of a given branch
     * and returns corresponding badge svg.
     *
     * @see "[Latest Build Status Badge API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#badge)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> badge(String owner, String repo, String branch) throws APICallFailure {
        return apiCall(null, "GET", "/repository/" + uriEncode(owner) + "/" + uriEncode(repo) + "/" + uriEncode(branch) + "/badge.svg", EmptyPayload.class);
    }

    /**
     * Returns any repository metadata that is
     * useful within Taskcluster related services.
     *
     * @see "[Get Repository Info API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#repository)"
     */
    public CallSummary<EmptyPayload, RepositoryResponse> repository(String owner, String repo) throws APICallFailure {
        return apiCall(null, "GET", "/repository/" + uriEncode(owner) + "/" + uriEncode(repo), RepositoryResponse.class);
    }

    /**
     * For a given branch of a repository, this will always point
     * to a status page for the most recent task triggered by that
     * branch.
     * 
     * Note: This is a redirect rather than a direct link.
     *
     * @see "[Latest Status for Branch API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#latest)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> latest(String owner, String repo, String branch) throws APICallFailure {
        return apiCall(null, "GET", "/repository/" + uriEncode(owner) + "/" + uriEncode(repo) + "/" + uriEncode(branch) + "/latest", EmptyPayload.class);
    }

    /**
     * For a given changeset (SHA) of a repository, this will attach a "commit status"
     * on github. These statuses are links displayed next to each revision.
     * The status is either OK (green check) or FAILURE (red cross), 
     * made of a custom title and link.

     * Required scopes:
     *   github:create-status:<owner>/<repo>
     *
     * @see "[Post a status against a given changeset API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#createStatus)"
     */
    public CallSummary<CreateStatusRequest, EmptyPayload> createStatus(String owner, String repo, String sha, CreateStatusRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/repository/" + uriEncode(owner) + "/" + uriEncode(repo) + "/statuses/" + uriEncode(sha), EmptyPayload.class);
    }

    /**
     * For a given Issue or Pull Request of a repository, this will write a new message.

     * Required scopes:
     *   github:create-comment:<owner>/<repo>
     *
     * @see "[Post a comment on a given GitHub Issue or Pull Request API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#createComment)"
     */
    public CallSummary<CreateCommentRequest, EmptyPayload> createComment(String owner, String repo, String number, CreateCommentRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/repository/" + uriEncode(owner) + "/" + uriEncode(repo) + "/issues/" + uriEncode(number) + "/comments", EmptyPayload.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/github/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}