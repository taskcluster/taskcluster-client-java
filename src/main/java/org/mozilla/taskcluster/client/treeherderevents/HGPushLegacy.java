package org.mozilla.taskcluster.client.treeherderevents;

/**
 * BACKWARD COMPATABILITY: An HG job that only has a revision_hash.
 * Some repos like mozilla-beta have not yet merged in the code that
 * allows them access to the revision.
 *
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]
 */
public class HGPushLegacy {

    /**
     *
     * Possible values:
     *     * "hg.mozilla.org"
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/kind
     */
    public String kind;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/project
     */
    public String project;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/pushLogID
     */
    public int pushLogID;

    /**
     *
     * Syntax:     ^[0-9a-f]+$
     * Min length: 40
     * Max length: 40
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[1]/properties/revision_hash
     */
    public String revision_hash;
}
