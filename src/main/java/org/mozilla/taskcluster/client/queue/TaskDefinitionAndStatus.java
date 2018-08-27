package org.mozilla.taskcluster.client.queue;

/**
 * Task Definition and task status structure.
 *
 * See https://schemas.taskcluster.net/queue/v1/task-definition-and-status.json#
 */
public class TaskDefinitionAndStatus {

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-definition-and-status.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * See https://schemas.taskcluster.net/queue/v1/task-definition-and-status.json#/properties/task
     */
    public TaskDefinitionResponse task;
}
