package org.mozilla.taskcluster.client.index;

import java.util.Date;

/**
* Request to list tasks within a given namespace.
*
* See http://schemas.taskcluster.net/index/v1/list-tasks-request.json#
*/
public class ListTasksRequest {

    /**
     * A continuation token previously returned in a response to this list
     * request. This property is optional and should not be provided for first
     * requests.
     */
    public String continuationToken;

    /**
     * Maximum number of results per page. If there are more results than this
     * a continuation token will be return.
     */
    public int limit;
}