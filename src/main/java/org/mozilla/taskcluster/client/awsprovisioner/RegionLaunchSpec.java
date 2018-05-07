package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * LaunchSpecification entries unique to this Region
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/region-launch-spec.json#
 */
public class RegionLaunchSpec {

    /**
     * Per-region AMI ImageId
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/region-launch-spec.json#/properties/ImageId
     */
    public String ImageId;
}
