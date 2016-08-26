package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
 * An AMI Set from the provisioner
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#
 */
public class GetAMISetResponse {

    public class AmisEntry {

        /**
         * The AMI that uses HVM virtualization type
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#/properties/amis/items/properties/hvm
         */
        public String hvm;

        /**
         * The AMI that uses PV virtualization type
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#/properties/amis/items/properties/pv
         */
        public String pv;

        /**
         * The Amazon AWS region of the AMI set
         *
         * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#/properties/amis/items/properties/region
         */
        public String region;
    }

    /**
     * AMIs in this set
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#/properties/amis
     */
    public AmisEntry[] amis;

    /**
     * Name of the AMI set
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#/properties/id
     */
    public String id;

    /**
     * ISO Date string (e.g. new Date().toISOString()) which represents the time
     * when this AMI Set was last altered (inclusive of creation)
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/get-ami-set-response.json#/properties/lastModified
     */
    public Date lastModified;
}
