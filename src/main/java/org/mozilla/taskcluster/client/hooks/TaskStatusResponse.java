package org.mozilla.taskcluster.client.hooks;

/**
 * Response to a task status request
 *
 * See http://schemas.taskcluster.net/hooks/v1/task-status.json#
 */
public class TaskStatusResponse {

    /**
     * See http://schemas.taskcluster.net/hooks/v1/task-status.json#/properties/status
     */
    public TaskStatusStructure status;
}
