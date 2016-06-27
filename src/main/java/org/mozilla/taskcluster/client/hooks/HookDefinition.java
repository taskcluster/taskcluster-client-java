package org.mozilla.taskcluster.client.hooks;

/**
 * Definition of a hook that will create tasks when defined events occur.
 *
 * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#
 */
public class HookDefinition {

    /**
     * Deadline of the task, `pending` and `running` runs are resolved as **failed** if not resolved by other means before the deadline. Note, deadline cannot be more than5 days into the future.
     *
     * Must be specified as `A years B months C days D hours E minutes F seconds`, though you may leave out zeros. For more details see: `taskcluster.fromNow` in [taskcluster-client](https://github.com/taskcluster/taskcluster-client)
     *
     * Default:    "1 day"
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/deadline
     */
    public String deadline;

    /**
     * Task expiration, time at which task definition and status is deleted. Notice that all artifacts for the must have an expiration that is no later than this.
     *
     * Must be specified as `A years B months C days D hours E minutes F seconds`, though you may leave out zeros. For more details see: `taskcluster.fromNow` in [taskcluster-client](https://github.com/taskcluster/taskcluster-client)
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

    public class Metadata {

        /**
         * Long-form of the hook's purpose and behavior
         *
         * Max length: 32768
         *
         * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata/properties/description
         */
        public String description;

        /**
         * Whether to email the owner on an error creating the task.
         *
         * Default:    true
         *
         * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata/properties/emailOnError
         */
        public boolean emailOnError;

        /**
         * Human readable name of the hook
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata/properties/name
         */
        public String name;

        /**
         * Email of the person or group responsible for this hook.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata/properties/owner
         */
        public String owner;
    }

    /**
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/metadata
     */
    public Metadata metadata;

    /**
     * Definition of the times at which a hook will result in creation of a task.
     * If several patterns are specified, tasks will be created at any time
     * specified by one or more patterns.  Note that tasks may not be created
     * at exactly the time specified.
     *                     {$ref: "http://schemas.taskcluster.net/hooks/v1/schedule.json"}
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/schedule
     */
    public Object schedule;

    /**
     * See http://schemas.taskcluster.net/hooks/v1/hook-definition.json#/properties/task
     */
    public TaskTemplate task;
}
