package org.mozilla.taskcluster.client.ec2manager;

/**
 * This method provides a summary of the health in the EC2 account being managed.
 * Values for the overall account are provided, broken down by Region, Availability
 * Zone and Instance Type.
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#
 */
public class HealthOfTheEC2Account {

    public class RequestHealthEntry {

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/az
         */
        public String az;

        /**
         * The number of calls failed due to a misconfiguration of the worker type.  Due to the large number of error codes the EC2 API might return, this is a best effort categorization.  It covers codes which are like "Invalid%" using SQL pattern mattching on the codes from https://docs.aws.amazon.com/AWSEC2/latest/APIReference/errors-overview.html It is not categorized by which field was invalid in this response
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/configuration_issue
         */
        public int configuration_issue;

        /**
         * The total number of calls which failed, inrespective of why
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/failed
         */
        public int failed;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/instanceType
         */
        public String instanceType;

        /**
         * Number of runInstances calls which have failed because there aren't
         * enough hosts for the resources to be allocated.
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/insufficient_capacity
         */
        public int insufficient_capacity;

        /**
         * The number of calls which failed due to a limit being exceeded.
         * Due to the large number of error codes the EC2 API might return,
         * this is a best effort categorization.  It covers codes which are
         * like "%LimitExceeded" using SQL pattern mattching, but not
         * RequestLimitExceeded on the codes from
         * https://docs.aws.amazon.com/AWSEC2/latest/APIReference/errors-overview.html
         * It is not categorized by which limit was exceeded in this response
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/limit_exceeded
         */
        public int limit_exceeded;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/region
         */
        public String region;

        /**
         * The number of instances which have been requested successfully
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/successful
         */
        public int successful;

        /**
         * Number of calls which have been throttled in this region.  These
         * are errors with the code RequestLimitExceeded.
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items/properties/throttled_calls
         */
        public int throttled_calls;
    }

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth
     */
    public RequestHealthEntry[] requestHealth;

    public class RunningEntry {

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

    /**
     * An overview of currently running instances
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/running
     */
    public RunningEntry[] running;

    public class TerminationHealthEntry {

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/az
         */
        public String az;

        /**
         * A count of the instances which were shutdown cleanty.  For the
         * purposes of this API, a clean shutdown is one which was initiated
         * by us.  This includes API shutdowns or workers ending themselves.
         * It does not mean the actual workload ran successfully, rather that
         * we chose to terminate it
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/clean_shutdown
         */
        public int clean_shutdown;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/instanceType
         */
        public String instanceType;

        /**
         * The number of instances which were terminated due to a lack of
         * capacity.  More than likely, this will always be zero because the
         * new spot service is now synchronous, so runInstances calls should
         * fail
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/insufficient_capacity
         */
        public int insufficient_capacity;

        /**
         * The number of instances which were terminated due to not being able
         * to find the AMI
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/missing_ami
         */
        public int missing_ami;

        /**
         * The number of terminations which we cannot find a code.  This means
         * we cannot determine whether this should be classified as a good or
         * bad outcome.  The specific reason is that the code which polls for
         * termination reason was not able to run before the EC2 API dropped
         * the instance from its database
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/no_code
         */
        public int no_code;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/region
         */
        public String region;

        /**
         * The number of instances which were killed by the spot service
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/spot_kill
         */
        public int spot_kill;

        /**
         * The number of instances which failed to start, either because of an
         * error on our side or on the EC2 side
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/startup_failed
         */
        public int startup_failed;

        /**
         * The number of terminations which have a code which this code does
         * not recognize
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/unknown_code
         */
        public int unknown_code;

        /**
         * The number of instances which were terminated due to exceeding the
         * limit for number of ebs volumes
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items/properties/volume_limit_exceeded
         */
        public int volume_limit_exceeded;
    }

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth
     */
    public TerminationHealthEntry[] terminationHealth;
}
