package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items
 */
public class WorkerType {

    /**
     * Description of the worker-type.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/description
     */
    public String description;

    /**
     * Date and time after which the worker-type will be automatically
     * deleted by the queue.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/expires
     */
    public Date expires;

    /**
     * Date and time where the worker-type was last seen active
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/lastDateActive
     */
    public Date lastDateActive;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/provisionerId
     */
    public String provisionerId;

    /**
     * This is the stability of the worker-type. Accepted values:
     *  * `experimental`
     *  * `stable`
     *  * `deprecated`
     *
     * Possible values:
     *     * "experimental"
     *     * "stable"
     *     * "deprecated"
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/stability
     */
    public String stability;

    /**
     * WorkerType name.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/list-workertypes-response.json#/properties/workerTypes/items/properties/workerType
     */
    public String workerType;
}
