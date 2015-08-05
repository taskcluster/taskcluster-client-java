package org.mozilla.taskcluster.client.queueevents;

/**
* Message reporting a new artifact has been created for a given task.
*
* See http://schemas.taskcluster.net/queue/v1/artifact-created-message.json#
*/
public class ArtifactCreatedMessage {

    /**
     * Information about the artifact that was created
     */
    public Object artifact;

    /**
     * Id of the run on which artifact was created.
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Message version
     */
    public Object version;

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