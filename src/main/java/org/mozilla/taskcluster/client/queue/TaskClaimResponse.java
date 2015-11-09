package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* Response to a successful task claim
*
* See http://schemas.taskcluster.net/queue/v1/task-claim-response.json#
*/
public class TaskClaimResponse {

    /**
     * Temporary credentials granting `task.scopes` and the scope:
     * `queue:claim-task:<taskId>/<runId>` which allows the worker to reclaim
     * the task, upload artifacts and report task resolution.
     * 
     * The temporary credentials are set to expire after `takenUntil`. They
     * won't expire exactly at `takenUntil` but shortly after, hence, requests
     * coming close `takenUntil` won't have problems even if there is a little
     * clock drift.
     * 
     * Workers should use these credentials when making requests on behalf of
     * a task. This includes requests to create artifacts, reclaiming the task
     * reporting the task `completed`, `failed` or `exception`.
     * 
     * Note, a new set of temporary credentials is issued when the worker
     * reclaims the task.
     */
    public class Credentials {

        /**
         * The `accessToken` for the temporary credentials.
         */
        public String accessToken;

        /**
         * The `certificate` for the temporary credentials, these are required
         * for the temporary credentials to work.
         */
        public String certificate;

        /**
         * The `clientId` for the temporary credentials.
         */
        public String clientId;
    }

    public Credentials credentials;

    /**
     * `run-id` assigned to this run of the task
     */
    public int runId;
    public TaskStatusStructure status;

    /**
     * Time at which the run expires and is resolved as `exception`,
     * with reason `claim-expired` if the run haven't been reclaimed.
     */
    public Date takenUntil;
    public TaskDefinitionResponse task;

    /**
     * Identifier for the worker-group within which this run started.
     */
    public String workerGroup;

    /**
     * Identifier for the worker executing this run.
     */
    public String workerId;
}