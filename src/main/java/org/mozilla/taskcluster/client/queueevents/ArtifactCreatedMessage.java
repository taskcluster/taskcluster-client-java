package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
 * Message reporting a new artifact has been created for a given task.
 *
 * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#
 */
public class ArtifactCreatedMessage {

    public class ArtifactCreated {

        /**
         * Mimetype for the artifact that was created.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/contentType
         */
        public String contentType;

        /**
         * Date and time after which the artifact created will be automatically
         * deleted by the queue.
         *
         * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/expires
         */
        public Date expires;

        /**
         * Name of the artifact that was created, this is useful if you want to
         * attempt to fetch the artifact. But keep in mind that just because an
         * artifact is created doesn't mean that it's immediately available.
         *
         * Max length: 1024
         *
         * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/name
         */
        public String name;

        /**
         * This is the `storageType` for the request that was used to create the
         * artifact.
         *
         * Possible values:
         *     * "blob"
         *     * "reference"
         *     * "error"
         *
         * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact/properties/storageType
         */
        public String storageType;
    }

    /**
     * Information about the artifact that was created
     *
     * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/artifact
     */
    public ArtifactCreated artifact;

    /**
     * Id of the run on which artifact was created.
     *
     * Mininum:    0
     * Maximum:    1000
     *
     * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/runId
     */
    public int runId;

    /**
     * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/status
     */
    public TaskStatusStructure status;

    /**
     * Message version
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/version
     */
    public int version;

    /**
     * Identifier for the worker-group within which the run with the created
     * artifacted is running.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/workerGroup
     */
    public String workerGroup;

    /**
     * Identifier for the worker within which the run with the created artifact
     * is running.
     *
     * Syntax:     ^([a-zA-Z0-9-_]*)$
     * Min length: 1
     * Max length: 22
     *
     * See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#/properties/workerId
     */
    public String workerId;
}
