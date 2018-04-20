package org.mozilla.taskcluster.client.ec2manager;

/**
 * This is a list of outcomes for a specific region, availability zone and
 * instance type.  These are calls to the EC2 runInstances method, which
 * is how we request instances.  If a call to this method is successful,
 * then we expect to get an instance to match
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/requestHealth/items
 */
public class Var1 {

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
