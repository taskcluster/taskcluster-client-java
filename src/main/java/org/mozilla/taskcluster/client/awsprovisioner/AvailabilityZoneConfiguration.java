package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Availability zone configuration
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/availability-zone-configuration.json#
 */
public class AvailabilityZoneConfiguration {

    /**
     * The AWS availability zone being configured.  Example: eu-central-1b
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/availability-zone-configuration.json#/properties/availabilityZone
     */
    public String availabilityZone;

    /**
     * LaunchSpecification entries unique to this AZ
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/availability-zone-configuration.json#/properties/launchSpec
     */
    public Object launchSpec;

    /**
     * The AWS region containing this availability zone.  Example: eu-central-1
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/availability-zone-configuration.json#/properties/region
     */
    public String region;

    /**
     * Static Secrets unique to this AZ
     *
     * Default:    {}
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/availability-zone-configuration.json#/properties/secrets
     */
    public Object secrets;

    /**
     * UserData entries unique to this AZ
     *
     * Default:    {}
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/availability-zone-configuration.json#/properties/userData
     */
    public Object userData;
}
