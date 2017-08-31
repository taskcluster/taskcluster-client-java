package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request to update a provisioner.
 *
 * See http://schemas.taskcluster.net/queue/v1/update-provisioner-request.json#
 */
public class ProvisionerRequest {

    /**
     * Description of the provisioner.
     *
     * See http://schemas.taskcluster.net/queue/v1/update-provisioner-request.json#/properties/description
     */
    public String description;

    /**
     * Date and time after which the provisioner will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/update-provisioner-request.json#/properties/expires
     */
    public Date expires;

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
     * See http://schemas.taskcluster.net/queue/v1/update-provisioner-request.json#/properties/stability
     */
    public String stability;
}
