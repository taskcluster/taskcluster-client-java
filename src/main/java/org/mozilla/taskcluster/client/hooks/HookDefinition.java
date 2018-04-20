package org.mozilla.taskcluster.client.hooks;

/**
 * Definition of a hook that will create tasks when defined events occur.
 *
 * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#
 */
public class HookDefinition {

    /**
     * Use of this field is deprecated; use `deadline: {$fromNow: ..}` in the task template instead.
     *
     * Default:    "1 day"
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/deadline
     */
    public String deadline;

    /**
     * Use of this field is deprecated; use `expires: {$fromNow: ..}` in the task template instead.
     *
     * Default:    "3 months"
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/expires
     */
    public String expires;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/hookGroupId
     */
    public String hookGroupId;

    /**
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/hookId
     */
    public String hookId;

    /**
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata
     */
    public HookMetadata metadata;

    /**
     * Definition of the times at which a hook will result in creation of a task.
     * If several patterns are specified, tasks will be created at any time
     * specified by one or more patterns.  Note that tasks may not be created
     * at exactly the time specified.
     *                     {$ref: "http://schemas.taskcluster.net/hooks/v1/schedule.json#"}
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/schedule
     */
    public Object schedule;

    /**
     * Template for the task definition.  This is rendered using [JSON-e](https://taskcluster.github.io/json-e/)
     * as described in https://docs.taskcluster.net/reference/core/taskcluster-hooks/docs/firing-hooks to produce
     * a task definition that is submitted to the Queue service.
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/task
     */
    public Object task;

    /**
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/triggerSchema
     */
    public Object triggerSchema;
}
