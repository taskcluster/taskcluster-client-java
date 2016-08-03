package org.mozilla.taskcluster.client.queueevents;

/**
 * Message written once a task group has no tasks to be run. It is
 * possible for a task group to later have another task added, in which
 * case this message will be sent again once it finishes.
 *
 * See http://schemas.taskcluster.net/queue/v1/task-group-resolved.json#
 */
public class TaskGroupResolved {

    /**
     * Identifier for the scheduler that created this task-group.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/task-group-resolved.json#/properties/schedulerId
     */
    public String schedulerId;

    /**
     * Identifier for the task-group being listed.
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See http://schemas.taskcluster.net/queue/v1/task-group-resolved.json#/properties/taskGroupId
     */
    public String taskGroupId;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/queue/v1/task-group-resolved.json#/properties/version
     */
    public int version;
}
