package org.mozilla.taskcluster.client.scheduler;

/**
 * Response containing the status structure for a task-graph
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status-response.json#
 */
public class TaskGraphStatusResponse {

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status-response.json#/properties/status
     */
    public TaskGraphStatusStructure status;
}
