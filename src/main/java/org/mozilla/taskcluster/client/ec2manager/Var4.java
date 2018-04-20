package org.mozilla.taskcluster.client.ec2manager;

import java.util.Date;

/**
 * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items
 */
public class Var4 {

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/az
     */
    public String az;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/code
     */
    public String code;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/instanceType
     */
    public String instanceType;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/message
     */
    public String message;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/region
     */
    public String region;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/time
     */
    public Date time;

    /**
     *
     * Possible values:
     *     * "instance-request"
     *     * "termination"
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/type
     */
    public String type;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/workerType
     */
    public String workerType;
}
