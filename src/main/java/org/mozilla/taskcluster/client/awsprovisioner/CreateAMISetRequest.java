package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Create a new AMI Set
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/create-ami-set-request.json#
 */
public class CreateAMISetRequest {

    public class AmisEntry {

        /**
         * The AMI that uses HVM virtualization type
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/create-ami-set-request.json#/properties/amis/items/properties/hvm
         */
        public String hvm;

        /**
         * The AMI that uses PV virtualization type
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/create-ami-set-request.json#/properties/amis/items/properties/pv
         */
        public String pv;

        /**
         * The Amazon AWS region of the AMI set
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/create-ami-set-request.json#/properties/amis/items/properties/region
         */
        public String region;
    }

    /**
     * AMIs in this set
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/create-ami-set-request.json#/properties/amis
     */
    public AmisEntry[] amis;
}
