package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response containing information about a provisioner.
 *
 * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#
 */
public class ProvisionerResponse {

    /**
     * Description of the provisioner.
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/description
     */
    public String description;

    /**
     * Date and time after which the provisioner will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Date of the last time this provisioner was seen active. `lastDateActive` is updated every 6 hours
     * but may be off by up-to 6 hours. Nonetheless, `lastDateActive` is a good indicator
     * of when the provisioner was last seen active.
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/lastDateActive
     */
    public Date lastDateActive;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     * This is the stability of the provisioner. Accepted values:
     *   * `experimental`
     *   * `stable`
     *   * `deprecated`
     *
     * Possible values:
     *     * "experimental"
     *     * "stable"
     *     * "deprecated"
     *
     * See http://schemas.taskcluster.net/queue/v1/provisioner-response.json#/properties/stability
     */
    public String stability;
}
