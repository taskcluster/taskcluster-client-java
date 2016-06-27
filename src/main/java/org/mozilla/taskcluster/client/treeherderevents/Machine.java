package org.mozilla.taskcluster.client.treeherderevents;

/**
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/definitions/machine
 */
public class Machine {

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 25
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/definitions/machine/properties/architecture
     */
    public String architecture;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 50
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/definitions/machine/properties/name
     */
    public String name;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 25
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/definitions/machine/properties/os
     */
    public String os;

    /**
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 100
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/definitions/machine/properties/platform
     */
    public String platform;
}
