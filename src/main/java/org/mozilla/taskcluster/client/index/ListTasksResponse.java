package org.mozilla.taskcluster.client.index;

import java.util.Date;

/**
 * Representation of an indexed task.
 *
 * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#
 */
public class ListTasksResponse {

    /**
     * A continuation token is returned if there are more results than listed
     * here. You can optionally provide the token in the request payload to
     * load the additional results.
     *
     * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/continuationToken
     */
    public String continuationToken;

    public class TasksEntry {

        /**
         * Data that was reported with the task. This is an arbitrary JSON
         * object.
         *
         * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/tasks/items/properties/data
         */
        public Object data;

        /**
         * Date at which this entry expires from the task index.
         *
         * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/tasks/items/properties/expires
         */
        public Date expires;

        /**
         * Namespace of the indexed task, used to find the indexed task in the
         * index.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/tasks/items/properties/namespace
         */
        public String namespace;

        /**
         * If multiple tasks are indexed with the same `namespace` the task
         * with the highest `rank` will be stored and returned in later
         * requests. If two tasks has the same `rank` the latest task will be
         * stored.
         *
         * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/tasks/items/properties/rank
         */
        public int rank;

        /**
         * Unique task identifier, this is UUID encoded as
         * [URL-safe base64](http://tools.ietf.org/html/rfc4648#section-5) and
         * stripped of `=` padding.
         *
         * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
         *
         * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/tasks/items/properties/taskId
         */
        public String taskId;
    }

    /**
     * List of tasks.
     *
     * See http://schemas.taskcluster.net/index/v1/list-tasks-response.json#/properties/tasks
     */
    public TasksEntry[] tasks;
}
