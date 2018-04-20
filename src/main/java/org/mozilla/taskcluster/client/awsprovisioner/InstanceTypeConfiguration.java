package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Instance Type configuration
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items
 */
public class InstanceTypeConfiguration {

    /**
     * This number represents the number of tasks that this instance type
     * is capable of running concurrently.  This is used by the provisioner
     * to know how many pending tasks to offset a pending instance of this
     * type by
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/capacity
     */
    public int capacity;

    /**
     * InstanceType name for Amazon.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/instanceType
     */
    public String instanceType;

    /**
     * LaunchSpecification entries unique to this InstanceType
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/launchSpec
     */
    public Object launchSpec;

    /**
     * Scopes which should be included for this InstanceType.  Scopes must
     * be composed of printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/scopes
     */
    public String[] scopes;

    /**
     * Static Secrets unique to this InstanceType
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/secrets
     */
    public Object secrets;

    /**
     * UserData entries unique to this InstanceType
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/userData
     */
    public Object userData;

    /**
     * This number is a relative measure of performance between two instance
     * types.  It is multiplied by the spot price from Amazon to figure out
     * which instance type is the cheapest one
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/instanceTypes/items/properties/utility
     */
    public int utility;
}
