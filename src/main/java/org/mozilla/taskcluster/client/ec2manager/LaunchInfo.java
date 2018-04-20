package org.mozilla.taskcluster.client.ec2manager;

/**
 * This is an EC2-Manager specific wrapping of the request body for the
 * upstream EC2 API.  Values from this are passed through verbatim.  A small
 * number of checks are done on the data before making the call, as well as
 * having some schema keys set to ensure certain values are either present
 * or absent
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/LaunchInfo
 */
public class LaunchInfo {

    /**
     * This is the AMI Identifier for this spot request.  This image must
     * already exist and must be in the region of the request.  Note that
     * AMI images are per-region, so you must copy or regenerate the image
     * for each region.
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/LaunchInfo/properties/ImageId
     */
    public String ImageId;

    /**
     * The instance type to use for this spot request
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/LaunchInfo/properties/InstanceType
     */
    public String InstanceType;

    /**
     * A valid EC2 KeyPair name.  The KeyPair must already exist
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/LaunchInfo/properties/KeyName
     */
    public String KeyName;

    /**
     * This is a list of the security groups this image will use.  These
     * groups must already exist in the region.
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/LaunchInfo/properties/SecurityGroups
     */
    public String[] SecurityGroups;
}
