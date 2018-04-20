package org.mozilla.taskcluster.client.hooks;

/**
 * Definition of a hook that can create tasks at defined times.
 *
 * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#
 */
public class HookCreationRequest {

    /**
     * Use of this field is deprecated; use `deadline: {$fromNow: ..}` in the task template instead.
     *
     * Default:    "1 day"
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/deadline
     */
    public String deadline;

    /**
     * Use of this field is deprecated; use `expires: {$fromNow: ..}` in the task template instead.
     *
     * Default:    "3 months"
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/expires
     */
    public String expires;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 64
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/hookGroupId
     */
    public String hookGroupId;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 64
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/hookId
     */
    public String hookId;

    /**
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/metadata
     */
    public HookMetadata metadata;

    /**
     * Definition of the times at which a hook will result in creation of a task.
     * If several patterns are specified, tasks will be created at any time
     * specified by one or more patterns.
     *
     * Default:    []
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/schedule
     */
    public String[] schedule;

    /**
     * Template for the task definition.  This is rendered using [JSON-e](https://taskcluster.github.io/json-e/)
     * as described in https://docs.taskcluster.net/reference/core/taskcluster-hooks/docs/firing-hooks to produce
     * a task definition that is submitted to the Queue service.
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/task
     */
    public Object task;

    /**
     *
     * Default:    {
     *   "additionalProperties": false,
     *   "type": "object"
     * }
     *
     * See http://schemas.taskcluster.net/hooks/v1/create-hook-request.json#/properties/triggerSchema
     */
    public Object triggerSchema;
}
