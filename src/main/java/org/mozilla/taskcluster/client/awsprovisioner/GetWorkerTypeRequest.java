package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
* A worker launchSpecification and required metadata
*
* See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#
*/
public class GetWorkerTypeRequest {

    /**
     * True if this worker type is allowed on demand instances.  Currently
     * ignored
     */
    public boolean canUseOndemand;

    /**
     * True if this worker type is allowed spot instances.  Currently ignored
     * as all instances are Spot
     */
    public boolean canUseSpot;
    public class InstanceTypes {

        /**
         * This number represents the number of tasks that this instance type
         * is capable of running concurrently.  This is used by the provisioner
         * to know how many pending tasks to offset a pending instance of this
         * type by
         */
        public int capacity;

        /**
         * InstanceType name for Amazon.
         */
        public String instanceType;

        /**
         * LaunchSpecification entries unique to this InstanceType
         */
        public Object launchSpec;

        /**
         * Scopes which should be included for this InstanceType
         */
        public String[] scopes;

        /**
         * Static Secrets unique to this InstanceType
         */
        public Object secrets;

        /**
         * UserData entries unique to this InstanceType
         */
        public Object userData;

        /**
         * This number is a relative measure of performance between two instance
         * types.  It is multiplied by the spot price from Amazon to figure out
         * which instance type is the cheapest one
         */
        public int utility;
    }

    public InstanceTypes[] instanceTypes;

    /**
     * ISO Date string (e.g. new Date().toISOString()) which represents the time
     * when this worker type definition was last altered (inclusive of creation)
     */
    public Date lastModified;

    /**
     * Launch Specification entries which are used in all regions and all instance types
     */
    public Object launchSpec;

    /**
     * Maximum number of capacity units to be provisioned.
     */
    public int maxCapacity;

    /**
     * Maximum price we'll pay.  Like minPrice, this takes into account the
     * utility factor when figuring out what the actual SpotPrice submitted
     * to Amazon will be
     */
    public int maxPrice;

    /**
     * Minimum number of capacity units to be provisioned.  A capacity unit
     * is an abstract unit of capacity, where one capacity unit is roughly
     * one task which should be taken off the queue
     */
    public int minCapacity;

    /**
     * Minimum price to pay for an instance.  A Price is considered to be the
     * Amazon Spot Price multiplied by the utility factor of the InstantType
     * as specified in the instanceTypes list.  For example, if the minPrice
     * is set to $0.5 and the utility factor is 2, the actual minimum bid
     * used will be $0.25
     */
    public int minPrice;
    public class Regions {

        /**
         * LaunchSpecification entries unique to this Region
         */
        public class LaunchSpec {

            /**
             * Per-region AMI ImageId
             */
            public String ImageId;
        }

        public LaunchSpec launchSpec;

        /**
         * The Amazon AWS Region being configured.  Example: us-west-1
         */
        public String region;

        /**
         * Scopes which should be included for this Region
         */
        public String[] scopes;

        /**
         * Static Secrets unique to this Region
         */
        public Object secrets;

        /**
         * UserData entries unique to this Region
         */
        public Object userData;
    }

    public Regions[] regions;

    /**
     * A scaling ratio of `0.2` means that the provisioner will attempt to keep
     * the number of pending tasks around 20% of the provisioned capacity.
     * This results in pending tasks waiting 20% of the average task execution
     * time before starting to run.
     * A higher scaling ratio often results in better utilization and longer
     * waiting times. For workerTypes running long tasks a short scaling ratio
     * may be prefered, but for workerTypes running quick tasks a higher scaling
     * ratio may increase utilization without major delays.
     * If using a scaling ratio of 0, the provisioner will attempt to keep the
     * capacity of pending spot requests equal to the number of pending tasks.
     */
    public int scalingRatio;

    /**
     * Scopes to issue credentials to for all regions
     */
    public String[] scopes;

    /**
     * Static secrets entries which are used in all regions and all instance types
     */
    public Object secrets;

    /**
     * UserData entries which are used in all regions and all instance types
     */
    public Object userData;

    /**
     * The ID of the workerType
     */
    public String workerType;
}