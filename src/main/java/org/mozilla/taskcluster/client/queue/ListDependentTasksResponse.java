package org.mozilla.taskcluster.client.queue;

/**
 * Response from a `listDependentTasks` request.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-dependent-tasks-response.json#
 */
public class ListDependentTasksResponse {

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of dependent tasks.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called `listDependentTasks` with
     * `continuationToken` until you get a result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-dependent-tasks-response.json#/properties/continuationToken
     */
    public String continuationToken;

    /**
     * Identifier for the task whose dependents are being listed.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/queue/v1/list-dependent-tasks-response.json#/properties/taskId
     */
    public String taskId;

    public class TasksEntry {

        /**
         * See http://schemas.taskcluster.net/queue/v1/list-dependent-tasks-response.json#/properties/tasks/items/properties/status
         */
        public TaskStatusStructure status;

        /**
         * See http://schemas.taskcluster.net/queue/v1/list-dependent-tasks-response.json#/properties/tasks/items/properties/task
         */
        public TaskDefinitionResponse task;
    }

    /**
     * List of tasks that have `taskId` in the `task.dependencies` property.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-dependent-tasks-response.json#/properties/tasks
     */
    public TasksEntry[] tasks;
}
