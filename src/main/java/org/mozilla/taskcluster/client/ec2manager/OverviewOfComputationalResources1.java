package org.mozilla.taskcluster.client.ec2manager;

/**
 * Overview of computational resources for a given worker type
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-state.json#
 */
public class OverviewOfComputationalResources1 {

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/worker-type-state.json#/properties/instances
     */
    public Object[] instances;
}
