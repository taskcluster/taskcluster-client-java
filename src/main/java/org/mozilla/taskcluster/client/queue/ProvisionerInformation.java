package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items
 */
public class ProvisionerInformation {

    /**
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/actions
     */
    public Action[] actions;

    /**
     * Description of the provisioner.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/description
     */
    public String description;

    /**
     * Date and time after which the provisioner created will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/expires
     */
    public Date expires;

    /**
     * Date and time where the provisioner was last seen active
     *
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/lastDateActive
     */
    public Date lastDateActive;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/provisionerId
     */
    public String provisionerId;

    /**
     * This is the stability of the provisioner. Accepted values:
     *  * `experimental`
     *  * `stable`
     *  * `deprecated`
     *
     * Possible values:
     *     * "experimental"
     *     * "stable"
     *     * "deprecated"
     *
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/stability
     */
    public String stability;
}
