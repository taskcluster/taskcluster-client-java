package org.mozilla.taskcluster.client.awsprovisioner;

/**
* A summary of a worker type's current state, expresed in terms of capacity.
*
* See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-summary.json#
*/
public class WorkerTypeSummary {
    public int maxCapacity;
    public int minCapacity;
    public int pendingCapacity;
    public int requestedCapacity;
    public int runningCapacity;
    public String workerType;
}