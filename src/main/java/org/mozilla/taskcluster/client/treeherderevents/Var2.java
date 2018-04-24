package org.mozilla.taskcluster.client.treeherderevents;

import java.util.Date;

/**
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items
 */
public class Var2 {

    public class Var3 {

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

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors
     */
    public Var3[] errors;

    /**
     *
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/lineFinished
     */
    public int lineFinished;

    /**
     *
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/lineStarted
     */
    public int lineStarted;

    /**
     *
     * Min length: 1
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/name
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
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/result
     */
    public String result;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/timeFinished
     */
    public Date timeFinished;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/timeStarted
     */
    public Date timeStarted;
}
