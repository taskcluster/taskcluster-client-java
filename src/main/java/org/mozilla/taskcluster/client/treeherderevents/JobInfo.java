package org.mozilla.taskcluster.client.treeherderevents;

/**
 * Definition of the Job Info for a job.  These are extra data
 * fields that go along with a job that will be displayed in
 * the details panel within Treeherder.
 *
 * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/jobInfo
 */
public class JobInfo {

    public class Link {

        /**
         *
         * Min length: 1
         * Max length: 70
         *
         * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/label
         */
        public String label;

        /**
         *
         * Min length: 1
         * Max length: 125
         *
         * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/linkText
         */
        public String linkText;

        /**
         *
         * Max length: 512
         *
         * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/url
         */
        public String url;
    }

    /**
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links
     */
    public Link[] links;

    /**
     * Plain text description of the job and its state.  Submitted with
     * the final message about a task.
     *
     * See https://schemas.taskcluster.net/treeherder/v1/pulse-job.json#/properties/jobInfo/properties/summary
     */
    public String summary;
}
