package org.mozilla.taskcluster.client.treeherderevents;

import java.util.Date;

/**
 * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items
 */
public class Step {

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

    /**
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors
     */
    public Error[] errors;

    /**
     *
     * Mininum:    0
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/lineFinished
     */
    public int lineFinished;

    /**
     *
     * Mininum:    0
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/lineStarted
     */
    public int lineStarted;

    /**
     *
     * Min length: 1
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/name
     */
    public String name;

    /**
     *
     * Possible values:
     *     * "success"
     *     * "fail"
     *     * "exception"
     *     * "canceled"
     *     * "unknown"
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/result
     */
    public String result;

    /**
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/timeFinished
     */
    public Date timeFinished;

    /**
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/timeStarted
     */
    public Date timeStarted;
}
