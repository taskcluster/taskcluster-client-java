package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]
 */
public class HGPush {

    /**
     *
     * Possible values:
     *     * "hg.mozilla.org"
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/kind
     */
    public String kind;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/project
     */
    public String project;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/pushLogID
     */
    public int pushLogID;

    /**
     *
     * Syntax:     ^[0-9a-f]+$
     * Min length: 40
     * Max length: 40
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin/oneOf[0]/properties/revision
     */
    public String revision;
}
