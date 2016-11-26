package org.mozilla.taskcluster.client.queue;

/**
 * Response from a `listTaskGroup` request.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#
 */
public class ListTaskGroupResponse {

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of tasks in the task-group.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called `listTaskGroup` with `continuationToken`
     * until you get a result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#/properties/continuationToken
     */
    public String continuationToken;

    /**
     * Identifier for the task-group being listed.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#/properties/taskGroupId
     */
    public String taskGroupId;

    public class TaskDefinitionAndStatus {

        /**
         * See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#/properties/tasks/items/properties/status
         */
        public TaskStatusStructure status;

        /**
         * See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#/properties/tasks/items/properties/task
         */
        public TaskDefinitionResponse task;
    }

    /**
     * List of tasks in this task-group.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#/properties/tasks
     */
    public TaskDefinitionAndStatus[] tasks;
}
