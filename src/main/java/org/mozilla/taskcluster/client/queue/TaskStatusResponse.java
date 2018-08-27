package org.mozilla.taskcluster.client.queue;

/**
 * Response to a task status request
 *
 * See https://schemas.taskcluster.net/queue/v1/task-status-response.json#
 */
public class TaskStatusResponse {

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-status-response.json#/properties/status
     */
    public TaskStatusStructure status;
}
