package org.mozilla.taskcluster.client.githubevents;

/**
* Message reporting that a GitHub pull request has occurred
*
* See http://schemas.taskcluster.net/github/v1/github-pull-request-message.json#
*/
public class GitHubPullRequestMessage {

    /**
     * The GitHub `action` which triggered an event.
     */
    public Object action;

    /**
     * Metadata describing the pull request.
     */
    public Object details;

    /**
     * The GitHub `organization` which had an event.
     */
    public String organization;

    /**
     * The GitHub `repository` which had an event.
     */
    public String repository;

    /**
     * Message version
     */
    public Object version;
}