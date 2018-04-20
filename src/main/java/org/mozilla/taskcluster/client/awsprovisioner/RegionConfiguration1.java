package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Region configuration
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-worker-type-response.json#/properties/regions/items
 */
public class RegionConfiguration1 {

    public class LaunchSpec1 {

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
    public LaunchSpec1 launchSpec;

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
