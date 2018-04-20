package org.mozilla.taskcluster.client.ec2manager;

/**
 * See http://schemas.taskcluster.net/ec2-manager/v1/prices.json#/items
 */
public class Var5 {

    /**
     * EC2 instance type
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices.json#/items/properties/instanceType
     */
    public String instanceType;

    /**
     * Amount of dollars for an hour of usage for this configuration
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices.json#/items/properties/price
     */
    public int price;

    /**
     * EC2 region
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices.json#/items/properties/region
     */
    public String region;

    /**
     *
     * Possible values:
     *     * "spot"
     *     * "ondemand"
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices.json#/items/properties/type
     */
    public String type;

    /**
     * EC2 availability zone identifier
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/prices.json#/items/properties/zone
     */
    public String zone;
}
