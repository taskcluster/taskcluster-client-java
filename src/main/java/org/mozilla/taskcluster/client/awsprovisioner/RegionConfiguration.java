package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Region configuration
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items
 */
public class RegionConfiguration {

    public class LaunchSpec {

        /**
         * Per-region AMI ImageId
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/launchSpec/properties/ImageId
         */
        public String ImageId;
    }

    /**
     * LaunchSpecification entries unique to this Region
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/launchSpec
     */
    public LaunchSpec launchSpec;

    /**
     * The Amazon AWS Region being configured.  Example: us-west-1
     *
     * Possible values:
     *     * "us-west-2"
     *     * "us-east-1"
     *     * "us-east-2"
     *     * "us-west-1"
     *     * "eu-central-1"
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/region
     */
    public String region;

    /**
     * Scopes which should be included for this Region.  Scopes must be
     * composed of printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/scopes
     */
    public String[] scopes;

    /**
     * Static Secrets unique to this Region
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/secrets
     */
    public Object secrets;

    /**
     * UserData entries unique to this Region
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-worker-type-request.json#/properties/regions/items/properties/userData
     */
    public Object userData;
}
