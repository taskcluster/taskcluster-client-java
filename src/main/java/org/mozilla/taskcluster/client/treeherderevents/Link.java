package org.mozilla.taskcluster.client.treeherderevents;

/**
 * List of URLs shown as key/value pairs.  Shown as:
 * "<label>: <linkText>" where linkText will be a link to the url.
 *
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items
 */
public class Link {

    /**
     *
     * Min length: 1
     * Max length: 70
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/label
     */
    public String label;

    /**
     *
     * Min length: 1
     * Max length: 125
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/linkText
     */
    public String linkText;

    /**
     *
     * Max length: 512
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/url
     */
    public String url;
}
