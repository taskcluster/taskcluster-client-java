package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Definition of a task that can be scheduled
 *
 * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#
 */
public class TaskDefinitionRequest {

    /**
     * Creation time of task
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/created
     */
    public Date created;

    /**
     * Deadline of the task, `pending` and `running` runs are
     * resolved as **exception** if not resolved by other means
     * before the deadline. Note, deadline cannot be more than
     * 5 days into the future
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/deadline
     */
    public Date deadline;

    /**
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/dependencies
     */
    public String[] dependencies;

    /**
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/expires
     */
    public Date expires;

    /**
     *
     * Default:    {}
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/extra
     */
    public Object extra;

    /**
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/metadata
     */
    public TaskMetadata metadata;

    /**
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/payload
     */
    public Object payload;

    /**
     *
     * Default:    "lowest"
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/priority
     */
    public String priority;

    /**
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/provisionerId
     */
    public String provisionerId;

    /**
     *
     * Default:    "all-completed"
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/requires
     */
    public String requires;

    /**
     *
     * Default:    5
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/retries
     */
    public int retries;

    /**
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/routes
     */
    public String[] routes;

    /**
     *
     * Default:    "-"
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/schedulerId
     */
    public String schedulerId;

    /**
     *
     * Default:    []
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/scopes
     */
    public String[] scopes;

    /**
     *
     * Default:    {}
     *
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/tags
     */
    public Object tags;

    /**
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/taskGroupId
     */
    public String taskGroupId;

    /**
     * See https://schemas.taskcluster.net/queue/v1/create-task-request.json#/properties/workerType
     */
    public String workerType;
}
