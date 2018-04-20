package org.mozilla.taskcluster.client.ec2manager;

/**
 * Overview of computational resources for a given worker type
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#
 */
public class OverviewOfComputationalResources {

    public class Var {

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items/properties/count
         */
        public int count;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items/properties/instanceType
         */
        public String instanceType;

        /**
         *
         * Possible values:
         *     * "instance"
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending/items/properties/type
         */
        public String type;
    }

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/pending
     */
    public Var[] pending;

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-resources.json#/properties/running
     */
    public Object[] running;
}
