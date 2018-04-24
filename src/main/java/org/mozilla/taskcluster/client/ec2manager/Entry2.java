package org.mozilla.taskcluster.client.ec2manager;

/**
 * See http://schemas.taskcluster.net/ec2-manager/v1/prices-request.json#/items
 */
public class Entry2 {

    /**
     *
     * Possible values:
     *     * "instanceType"
     *     * "region"
     *     * "price"
     *     * "minPrice"
     *     * "maxPrice"
     *     * "zone"
     *     * "type"
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices-request.json#/items/properties/key
     */
    public String key;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices-request.json#/items/properties/restriction
     */
    public Object restriction;
}
