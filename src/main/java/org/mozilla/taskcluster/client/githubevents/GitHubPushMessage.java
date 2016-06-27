package org.mozilla.taskcluster.client.githubevents;

/**
 * Message reporting that a GitHub push has occurred
 *
 * See http://schemas.taskcluster.net/github/v1/github-push-message.json#
 */
public class GitHubPushMessage {

    /**
     * Metadata describing the push.
     *
     * See http://schemas.taskcluster.net/github/v1/github-push-message.json#/properties/details
     */
    public Object details;

    /**
     * The GitHub `organization` which had an event.
     *
     * Syntax:     ^([a-zA-Z0-9-_%]*)$
     * Min length: 1
     * Max length: 100
     *
     * See http://schemas.taskcluster.net/github/v1/github-push-message.json#/properties/organization
     */
    public String organization;

    /**
     * The GitHub `repository` which had an event.
     *
     * Syntax:     ^([a-zA-Z0-9-_%]*)$
     * Min length: 1
     * Max length: 100
     *
     * See http://schemas.taskcluster.net/github/v1/github-push-message.json#/properties/repository
     */
    public String repository;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/github/v1/github-push-message.json#/properties/version
     */
    public Object version;
}
