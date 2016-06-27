package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[2]
 */
public class GithubPullRequest {

    /**
     *
     * Possible values:
     *     * "github.com"
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[2]/properties/kind
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
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[2]/properties/owner
     */
    public String owner;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[2]/properties/project
     */
    public String project;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[2]/properties/pullRequestID
     */
    public int pullRequestID;

    /**
     *
     * Min length: 40
     * Max length: 40
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[2]/properties/revision
     */
    public String revision;
}
