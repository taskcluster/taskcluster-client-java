package org.mozilla.taskcluster.client.ec2manager;

/**
 * This is a list of summaries of instances which have terminated
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/health.json#/properties/terminationHealth/items
 */
public class Var3 {

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
