package org.mozilla.taskcluster.client.treeherderevents;

/**
 * PREFERRED: An HG job that only has a revision.  This is for all
 * jobs going forward.
 *
 * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]
 */
public class HGPush {

    /**
     *
     * Possible values:
     *     * "hg.mozilla.org"
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/kind
     */
    public String kind;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/project
     */
    public String project;

    /**
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/pushLogID
     */
    public int pushLogID;

    /**
     *
     * Syntax:     ^[0-9a-f]+$
     * Min length: 40
     * Max length: 40
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/revision
     */
    public String revision;
}
