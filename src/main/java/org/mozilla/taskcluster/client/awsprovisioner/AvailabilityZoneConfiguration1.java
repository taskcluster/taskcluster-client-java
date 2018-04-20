package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Availability zone configuration
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items
 */
public class AvailabilityZoneConfiguration1 {

    /**
     * The AWS availability zone being configured.  Example: eu-central-1b
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/availabilityZone
     */
    public String availabilityZone;

    /**
     * LaunchSpecification entries unique to this AZ
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/launchSpec
     */
    public Object launchSpec;

    /**
     * The AWS region containing this availability zone.  Example: eu-central-1
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/region
     */
    public String region;

    /**
     * Static Secrets unique to this AZ
     *
     * Default:    {}
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/secrets
     */
    public Object secrets;

    /**
     * UserData entries unique to this AZ
     *
     * Default:    {}
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/userData
     */
    public Object userData;
}
