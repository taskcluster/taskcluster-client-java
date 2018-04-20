package org.mozilla.taskcluster.client.ec2manager;

/**
 * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items
 */
public class Var {

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items/properties/count
     */
    public int count;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items/properties/instanceType
     */
    public String instanceType;

    /**
     *
     * Possible values:
     *     * "instance"
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items/properties/type
     */
    public String type;
}
