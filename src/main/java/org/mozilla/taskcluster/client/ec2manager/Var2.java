package org.mozilla.taskcluster.client.ec2manager;

/**
 * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/running/items
 */
public class Var2 {

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/running/items/properties/az
     */
    public String az;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/running/items/properties/instanceType
     */
    public String instanceType;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/running/items/properties/region
     */
    public String region;

    /**
     * The number of currently running instances in this configuration
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/running/items/properties/running
     */
    public int running;
}
