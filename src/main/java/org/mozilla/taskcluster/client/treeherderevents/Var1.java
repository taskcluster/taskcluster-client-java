package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items
 */
public class Var1 {

    /**
     *
     * Min length: 1
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items/properties/line
     */
    public String line;

    /**
     *
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items/properties/linenumber
     */
    public int linenumber;
}
