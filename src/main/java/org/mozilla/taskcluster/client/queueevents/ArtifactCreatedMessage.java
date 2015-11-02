package org.mozilla.taskcluster.client.queueevents;

import java.util.Date;

/**
* Message reporting a new artifact has been created for a given task.
*
* See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#
*/
public class ArtifactCreatedMessage {

    /**
     * Information about the artifact that was created
     */
    public class Artifact {

        /**
         * Mimetype for the artifact that was created.
         */
        public String contentType;

        /**
         * Date and time after which the artifact created will be automatically
         * deleted by the queue.
         */
        public Date expires;

        /**
         * Name of the artifact that was created, this is useful if you want to
         * attempt to fetch the artifact. But keep in mind that just because an
         * artifact is created doesn't mean that it's immediately available.
         */
        public String name;

        /**
         * This is the `storageType` for the request that was used to create the
         * artifact.
         */
        public String storageType;
    }

    public Artifact artifact;

    /**
     * Id of the run on which artifact was created.
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Message version
     */
    public int version;

    /**
     * Identifier for the worker-group within which the run with the created
     * artifacted is running.
     */
    public String workerGroup;

    /**
     * Identifier for the worker within which the run with the created artifact
     * is running.
     */
    public String workerId;
}