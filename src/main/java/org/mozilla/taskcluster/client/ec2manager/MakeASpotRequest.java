package org.mozilla.taskcluster.client.ec2manager;

/**
 * Presented here are the fields that are absolutely 100% required to make a
 * spot request.  The `LaunchSpecification` property is an opaque datastructure
 * from EC2, however the fields which we know are absolutely required are
 * described
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#
 */
public class MakeASpotRequest {

    /**
     * A ClientToken string per the implementation requirements of the EC2 api.
     * This string must be no more than 64 characters of ASCII.  We restrict the
     * client tokens further to alphanumeric ASCII with the addition of the `-`
     * and `_` characters
     *
     * Syntax:     ^[a-zA-Z0-0_-]{1,64}
     * Min length: 1
     * Max length: 64
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/ClientToken
     */
    public String ClientToken;

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

    /**
     * This is an EC2-Manager specific wrapping of the request body for the
     * upstream EC2 API.  Values from this are passed through verbatim.  A small
     * number of checks are done on the data before making the call, as well as
     * having some schema keys set to ensure certain values are either present
     * or absent
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/LaunchInfo
     */
    public LaunchInfo LaunchInfo;

    /**
     * The EC2 region in which this spot request is to be made.  This should be
     * the lower case api-identifier.  For example `us-east-1`
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/Region
     */
    public String Region;

    /**
     * Specify whether to use a spot request or an on-demand instance.  This is
     * not inferred from the SpotPrice being set or not because we want to allow
     * for the default behaviour for spot prices, which is to use the spot
     * market with a default price of the on-demand price
     *
     * Possible values:
     *     * "spot"
     *     * "on-demand"
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/RequestType
     */
    public String RequestType;

    /**
     * The actual price of the bid.  This is passed directly to the EC2 api and
     * so should not have any internal multipliers (e.g. capacity or utility)
     * applied
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/run-instance-request.json#/properties/SpotPrice
     */
    public int SpotPrice;
}
