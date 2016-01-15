package org.mozilla.taskcluster.client.queue;

/**
* Response from a `listTaskGroup` request.
*
* See http://schemas.taskcluster.net/queue/v1/list-task-group-response.json#
*/
public class ListTaskGroupResponse {

    /**
     * Opaque `continuationToken` to be given as query-string option if all the
     * tasks in the task-group wasn't included in this result.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called `listTaskGroup` with `continuationToken`
     * until you got a result without a `continuationToken`.
     */
    public String continuationToken;

    /**
     * List of `taskId` for tasks in this task-group.
     */
    public String[] members;

    /**
     * Identifier for the task-group being listed.
     */
    public String taskGroupId;
}