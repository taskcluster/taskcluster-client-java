package org.mozilla.taskcluster.client.githubevents;

/**
* Message reporting that a GitHub push has occurred
*
* See http://schemas.taskcluster.net/github/v1/github-push-message.json#
*/
public class GitHubPushMessage {

    /**
     * Metadata describing the push.
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