package org.mozilla.taskcluster.client.queue;

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
 *
 * See http://schemas.taskcluster.net/queue/v1/task-credentials.json#
 */
public class TaskCredentials {

    /**
     * The `accessToken` for the temporary credentials.
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/queue/v1/task-credentials.json#/properties/accessToken
     */
    public String accessToken;

    /**
     * The `certificate` for the temporary credentials, these are required
     * for the temporary credentials to work.
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/queue/v1/task-credentials.json#/properties/certificate
     */
    public String certificate;

    /**
     * The `clientId` for the temporary credentials.
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/queue/v1/task-credentials.json#/properties/clientId
     */
    public String clientId;
}
