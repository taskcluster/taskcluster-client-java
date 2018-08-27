package org.mozilla.taskcluster.client.githubevents;

/**
 * Message reporting that a GitHub pull request has occurred
 *
 * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#
 */
public class GitHubPullRequestMessage {

    /**
     * The GitHub `action` which triggered an event.
     *
     * Possible values:
     *     * "assigned"
     *     * "unassigned"
     *     * "labeled"
     *     * "unlabeled"
     *     * "opened"
     *     * "edited"
     *     * "closed"
     *     * "reopened"
     *     * "synchronize"
     *     * "review_requested"
     *     * "review_request_removed"
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/action
     */
    public String action;

    /**
     * The raw body of github event (for version 1)
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/body
     */
    public Object body;

    /**
     * The head ref of the event (for version 1)
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/branch
     */
    public String branch;

    /**
     * Metadata describing the pull request (for version 0)
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/details
     */
    public Object details;

    /**
     * The GitHub webhook deliveryId. Extracted from the header 'X-GitHub-Delivery'
     *
     * Syntax:     ^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}$
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/eventId
     */
    public String eventId;

    /**
     * The installation which had an event.
     *
     * Mininum:    0
     * Maximum:    10000000000
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/installationId
     */
    public int installationId;

    /**
     * The GitHub `organization` which had an event.
     *
     * Syntax:     ^([a-zA-Z0-9-_%]*)$
     * Min length: 1
     * Max length: 100
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/organization
     */
    public String organization;

    /**
     * The GitHub `repository` which had an event.
     *
     * Syntax:     ^([a-zA-Z0-9-_%]*)$
     * Min length: 1
     * Max length: 100
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/repository
     */
    public String repository;

    /**
     * The type of the event (for version 1)
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/tasks_for
     */
    public String tasks_for;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See https://schemas.taskcluster.net/github/v1/github-pull-request-message.json#/properties/version
     */
    public int version;
}
