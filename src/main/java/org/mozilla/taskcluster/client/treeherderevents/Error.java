package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items
 */
public class Error {

    /**
     *
     * Min length: 1
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items/properties/line
     */
    public String line;

    /**
     *
     * Mininum:    0
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items/properties/linenumber
     */
    public int linenumber;
}
