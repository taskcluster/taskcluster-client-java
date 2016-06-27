package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * A summary of a worker type's current state, expresed in terms of capacity.
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#
 */
public class WorkerTypeSummary {

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#/properties/maxCapacity
     */
    public int maxCapacity;

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#/properties/minCapacity
     */
    public int minCapacity;

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#/properties/pendingCapacity
     */
    public int pendingCapacity;

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#/properties/requestedCapacity
     */
    public int requestedCapacity;

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#/properties/runningCapacity
     */
    public int runningCapacity;

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#/properties/workerType
     */
    public String workerType;
}
