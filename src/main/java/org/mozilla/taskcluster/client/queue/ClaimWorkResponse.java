package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Response to an attempt to claim tasks for a worker to process.
 *
 * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#
 */
public class ClaimWorkResponse {

    public class TasksEntry {

        public class Credentials {

            /**
             * The `accessToken` for the temporary credentials.
             *
             * Min length: 1
             *
             * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/credentials/properties/accessToken
             */
            public String accessToken;

            /**
             * The `certificate` for the temporary credentials, these are required
             * for the temporary credentials to work.
             *
             * Min length: 1
             *
             * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/credentials/properties/certificate
             */
            public String certificate;

            /**
             * The `clientId` for the temporary credentials.
             *
             * Min length: 1
             *
             * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/credentials/properties/clientId
             */
            public String clientId;
        }

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
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/credentials
         */
        public Credentials credentials;

        /**
         * `run-id` assigned to this run of the task
         *
         * Mininum:    0
         * Maximum:    1000
         *
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/runId
         */
        public int runId;

        /**
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/status
         */
        public TaskStatusStructure status;

        /**
         * Time at which the run expires and is resolved as `exception`,
         * with reason `claim-expired` if the run haven't been reclaimed.
         *
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/takenUntil
         */
        public Date takenUntil;

        /**
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/task
         */
        public TaskDefinitionResponse task;

        /**
         * Identifier for the worker-group within which this run started.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/workerGroup
         */
        public String workerGroup;

        /**
         * Identifier for the worker executing this run.
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks/items/properties/workerId
         */
        public String workerId;
    }

    /**
     * List of task claims, may be empty if no tasks was claimed, in which case
     * the worker should sleep a tiny bit before polling again.
     *
     * See http://schemas.taskcluster.net/queue/v1/claim-work-response.json#/properties/tasks
     */
    public TasksEntry[] tasks;
}
