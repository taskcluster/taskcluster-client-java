package org.mozilla.taskcluster.client.githubevents;

/**
 * Message reporting that a GitHub release has occurred
 *
 * See https://schemas.taskcluster.net/github/v1/github-release-message.json#
 */
public class GitHubReleaseMessage {

    /**
     * The raw body of github event (for version 1)
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/body
     */
    public Object body;

    /**
     * The head ref of the event (for version 1)
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/branch
     */
    public String branch;

    /**
     * Metadata describing the release (for version 0)
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/details
     */
    public Object details;

    /**
     * The GitHub webhook deliveryId. Extracted from the header 'X-GitHub-Delivery'
     *
     * Syntax:     ^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}$
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/eventId
     */
    public String eventId;

    /**
     * The installation which had an event.
     *
     * Mininum:    0
     * Maximum:    10000000000
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/installationId
     */
    public int installationId;

    /**
     * The GitHub `organization` which had an event.
     *
     * Syntax:     ^([a-zA-Z0-9-_%]*)$
     * Min length: 1
     * Max length: 100
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/organization
     */
    public String organization;

    /**
     * The GitHub `repository` which had an event.
     *
     * Syntax:     ^([a-zA-Z0-9-_%]*)$
     * Min length: 1
     * Max length: 100
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/repository
     */
    public String repository;

    /**
     * The type of the event (for version 1)
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/tasks_for
     */
    public String tasks_for;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/github/v1/github-release-message.json#/properties/version
     */
    public int version;
}
