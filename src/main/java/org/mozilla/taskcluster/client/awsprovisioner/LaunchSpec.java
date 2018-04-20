package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * LaunchSpecification entries unique to this Region
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/launchSpec
 */
public class LaunchSpec {

    /**
     * Per-region AMI ImageId
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/launchSpec/properties/ImageId
     */
    public String ImageId;
}
