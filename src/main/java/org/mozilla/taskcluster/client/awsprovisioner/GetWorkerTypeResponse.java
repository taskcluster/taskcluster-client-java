package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
 * A worker launchSpecification and required metadata
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#
 */
public class GetWorkerTypeResponse {

    public class AvailabilityZonesEntry {

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
         * Default:    map[]
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/secrets
         */
        public Object secrets;

        /**
         * UserData entries unique to this AZ
         *
         * Default:    map[]
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones/items/properties/userData
         */
        public Object userData;
    }

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/availabilityZones
     */
    public AvailabilityZonesEntry[] availabilityZones;

    /**
     * True if this worker type is allowed on demand instances.  Currently
     * ignored
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/canUseOndemand
     */
    public boolean canUseOndemand;

    /**
     * True if this worker type is allowed spot instances.  Currently ignored
     * as all instances are Spot
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/canUseSpot
     */
    public boolean canUseSpot;

    /**
     * A string which describes what this image is for and hints on using it
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/description
     */
    public String description;

    public class InstanceTypesEntry {

        /**
         * This number represents the number of tasks that this instance type
         * is capable of running concurrently.  This is used by the provisioner
         * to know how many pending tasks to offset a pending instance of this
         * type by
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/capacity
         */
        public int capacity;

        /**
         * InstanceType name for Amazon.
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/instanceType
         */
        public String instanceType;

        /**
         * LaunchSpecification entries unique to this InstanceType
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/launchSpec
         */
        public Object launchSpec;

        /**
         * Scopes which should be included for this InstanceType.  Scopes must
         * be composed of printable ASCII characters and spaces.
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/scopes
         */
        public String[] scopes;

        /**
         * Static Secrets unique to this InstanceType
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/secrets
         */
        public Object secrets;

        /**
         * UserData entries unique to this InstanceType
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/userData
         */
        public Object userData;

        /**
         * This number is a relative measure of performance between two instance
         * types.  It is multiplied by the spot price from Amazon to figure out
         * which instance type is the cheapest one
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes/items/properties/utility
         */
        public int utility;
    }

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/instanceTypes
     */
    public InstanceTypesEntry[] instanceTypes;

    /**
     * ISO Date string (e.g. new Date().toISOString()) which represents the time
     * when this worker type definition was last altered (inclusive of creation)
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/lastModified
     */
    public Date lastModified;

    /**
     * Launch Specification entries which are used in all regions and all instance types
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/launchSpec
     */
    public Object launchSpec;

    /**
     * Maximum number of capacity units to be provisioned.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/maxCapacity
     */
    public int maxCapacity;

    /**
     * Maximum price we'll pay.  Like minPrice, this takes into account the
     * utility factor when figuring out what the actual SpotPrice submitted
     * to Amazon will be
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/maxPrice
     */
    public int maxPrice;

    /**
     * Minimum number of capacity units to be provisioned.  A capacity unit
     * is an abstract unit of capacity, where one capacity unit is roughly
     * one task which should be taken off the queue
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/minCapacity
     */
    public int minCapacity;

    /**
     * Minimum price to pay for an instance.  A Price is considered to be the
     * Amazon Spot Price multiplied by the utility factor of the InstantType
     * as specified in the instanceTypes list.  For example, if the minPrice
     * is set to $0.5 and the utility factor is 2, the actual minimum bid
     * used will be $0.25
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/minPrice
     */
    public int minPrice;

    /**
     * A string which identifies the owner of this worker type
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/owner
     */
    public String owner;

    public class RegionsEntry {

        public class LaunchSpec {

            /**
             * Per-region AMI ImageId
             *
             * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items/properties/launchSpec/properties/ImageId
             */
            public String ImageId;
        }

        /**
         * LaunchSpecification entries unique to this Region
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items/properties/launchSpec
         */
        public LaunchSpec launchSpec;

        /**
         * The Amazon AWS Region being configured.  Example: us-west-1
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items/properties/region
         */
        public String region;

        /**
         * Scopes which should be included for this Region.  Scopes must be
         * composed of printable ASCII characters and spaces.
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items/properties/scopes
         */
        public String[] scopes;

        /**
         * Static Secrets unique to this Region
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items/properties/secrets
         */
        public Object secrets;

        /**
         * UserData entries unique to this Region
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items/properties/userData
         */
        public Object userData;
    }

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions
     */
    public RegionsEntry[] regions;

    /**
     * A scaling ratio of `0.2` means that the provisioner will attempt to keep
     * the number of pending tasks around 20% of the provisioned capacity.
     * This results in pending tasks waiting 20% of the average task execution
     * time before starting to run.
     * A higher scaling ratio often results in better utilization and longer
     * waiting times. For workerTypes running long tasks a short scaling ratio
     * may be preferred, but for workerTypes running quick tasks a higher scaling
     * ratio may increase utilization without major delays.
     * If using a scaling ratio of 0, the provisioner will attempt to keep the
     * capacity of pending spot requests equal to the number of pending tasks.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/scalingRatio
     */
    public int scalingRatio;

    /**
     * Scopes to issue credentials to for all regions.  Scopes must be composed
     * of printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/scopes
     */
    public String[] scopes;

    /**
     * Static secrets entries which are used in all regions and all instance types
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/secrets
     */
    public Object secrets;

    /**
     * UserData entries which are used in all regions and all instance types
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/userData
     */
    public Object userData;

    /**
     * The ID of the workerType
     *
     * Syntax:     ^[A-Za-z0-9+/=_-]{1,22}$
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/workerType
     */
    public String workerType;
}
