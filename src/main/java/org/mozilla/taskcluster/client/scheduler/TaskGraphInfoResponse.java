package org.mozilla.taskcluster.client.scheduler;

/**
 * Response for a request for task-graph information
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#
 */
public class TaskGraphInfoResponse {

    public class MetaData {

        /**
         * Human readable description of task-graph, **explain** what it does!
         *
         * Max length: 32768
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/metadata/properties/description
         */
        public String description;

        /**
         * Human readable name of task-graph
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/metadata/properties/name
         */
        public String name;

        /**
         * E-mail of person who caused this task-graph, e.g. the person who did `hg push`
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/metadata/properties/owner
         */
        public String owner;

        /**
         * Link to source of this task-graph, should specify file, revision and repository
         *
         * Max length: 4096
         *
         * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/metadata/properties/source
         */
        public String source;
    }

    /**
     * Required task metadata
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/metadata
     */
    public MetaData metadata;

    /**
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/status
     */
    public TaskGraphStatusStructure status;

    /**
     * Arbitrary key-value tags (only strings limited to 4k)
     *
     * See http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#/properties/tags
     */
    public Object tags;
}
