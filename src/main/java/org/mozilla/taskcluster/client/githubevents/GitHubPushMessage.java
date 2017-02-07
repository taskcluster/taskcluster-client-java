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
     * The GitHub webhook deliveryId. Extracted from the header 'X-GitHub-Delivery'
     *
     * Syntax:     ^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}$
     *
     * See http://schemas.taskcluster.net/github/v1/github-push-message.json#/properties/eventId
     */
    public String eventId;

    /**
     * The installation which had an event.
     *
     * Min length: 0
     * Max length: 10000000000
     *
     * See http://schemas.taskcluster.net/github/v1/github-push-message.json#/properties/installationId
     */
    public int installationId;

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
