package org.mozilla.taskcluster.client.queue;

/**
* Response to a task status request
*
* See http://schemas.taskcluster.net/queue/v1/task-status-response.json#
*/
public class TaskStatusResponse {
    public TaskStatusStructure status;
}