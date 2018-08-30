package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display
 */
public class Display {

    /**
     *
     * Mininum:    1
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display/properties/chunkCount
     */
    public int chunkCount;

    /**
     *
     * Mininum:    1
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display/properties/chunkId
     */
    public int chunkId;

    /**
     *
     * Min length: 1
     * Max length: 100
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display/properties/groupName
     */
    public String groupName;

    /**
     *
     * Min length: 1
     * Max length: 25
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display/properties/groupSymbol
     */
    public String groupSymbol;

    /**
     *
     * Min length: 1
     * Max length: 100
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display/properties/jobName
     */
    public String jobName;

    /**
     *
     * Min length: 0
     * Max length: 25
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/display/properties/jobSymbol
     */
    public String jobSymbol;
}
