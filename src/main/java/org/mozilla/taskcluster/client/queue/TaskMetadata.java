package org.mozilla.taskcluster.client.queue;

/**
 * Required task metadata
 *
 * See http://schemas.taskcluster.net/queue/v1/task-metadata.json#
 */
public class TaskMetadata {

    /**
     * Human readable description of the task, please **explain** what the
     * task does. A few lines of documentation is not going to hurt you.
     *
     * Max length: 32768
     *
     * See http://schemas.taskcluster.net/queue/v1/task-metadata.json#/properties/description
     */
    public String description;

    /**
     * Human readable name of task, used to very briefly given an idea about
     * what the task does.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/task-metadata.json#/properties/name
     */
    public String name;

    /**
     * E-mail of person who caused this task, e.g. the person who did
     * `hg push`. The person we should contact to ask why this task is here.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/task-metadata.json#/properties/owner
     */
    public String owner;

    /**
     * Link to source of this task, should specify a file, revision and
     * repository. This should be place someone can go an do a git/hg blame
     * to who came up with recipe for this task.
     *
     * Syntax:     ^https?://
     * Max length: 4096
     *
     * See http://schemas.taskcluster.net/queue/v1/task-metadata.json#/properties/source
     */
    public String source;
}
