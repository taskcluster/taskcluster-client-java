package org.mozilla.taskcluster.client.awsprovisionerevents;

/**
 * Message reporting that an action occured to a worker type
 *
 * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-message.json#
 */
public class WorkerTypeMessage {

    /**
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-message.json#/properties/version
     */
    public int version;

    /**
     * Name of the worker type which was created
     *
     * See http://schemas.taskcluster.net/aws-provisioner/v1/worker-type-message.json#/properties/workerType
     */
    public String workerType;
}
