package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]
 */
public class GithubPullRequest {

    /**
     *
     * Possible values:
     *     * "github.com"
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/kind
     */
    public String kind;

    /**
     * This could be the organization or the individual git username
     * depending on who owns the repo.
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/owner
     */
    public String owner;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/project
     */
    public String project;

    /**
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/pullRequestID
     */
    public int pullRequestID;

    /**
     *
     * Min length: 40
     * Max length: 40
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/revision
     */
    public String revision;
}
