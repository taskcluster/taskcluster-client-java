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
     */
    public String continuationToken;

    /**
     * Identifier for the task-group being listed.
     */
    public String taskGroupId;

    /**
     * List of tasks in this task-group.
     */
    public class Tasks {
        public TaskStatusStructure status;
        public TaskDefinitionResponse task;
    }

    public Tasks[] tasks;
}