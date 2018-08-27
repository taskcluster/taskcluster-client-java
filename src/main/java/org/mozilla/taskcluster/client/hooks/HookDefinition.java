package org.mozilla.taskcluster.client.hooks;

/**
 * Definition of a hook that will create tasks when defined events occur.
 *
 * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#
 */
public class HookDefinition {

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 64
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/hookGroupId
     */
    public String hookGroupId;

    /**
     *
     * Syntax:     ^([a-zA-Z0-9-_/]*)$
     * Min length: 1
     * Max length: 64
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/hookId
     */
    public String hookId;

    /**
     * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata
     */
    public HookMetadata metadata;

    /**
     * Definition of the times at which a hook will result in creation of a task.
     * If several patterns are specified, tasks will be created at any time
     * specified by one or more patterns.  Note that tasks may not be created
     * at exactly the time specified.
     *                     {$ref: "schedule.json#"}
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/schedule
     */
    public Object schedule;

    /**
     * Template for the task definition.  This is rendered using [JSON-e](https://taskcluster.github.io/json-e/)
     * as described in [firing hooks](/docs/reference/core/taskcluster-hooks/docs/firing-hooks) to produce
     * a task definition that is submitted to the Queue service.
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/task
     */
    public Object task;

    /**
     * See https://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/triggerSchema
     */
    public Object triggerSchema;
}
