// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/queue/v1/api.json
package org.mozilla.taskcluster.client.queue;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The queue, typically available at `queue.taskcluster.net`, is responsible
 * for accepting tasks and track their state as they are executed by
 * workers. In order ensure they are eventually resolved.
 * 
 * This document describes the API end-points offered by the queue. These 
 * end-points targets the following audience:
 *  * Schedulers, who create tasks to be executed,
 *  * Workers, who execute tasks, and
 *  * Tools, that wants to inspect the state of a task.
 *
 * @see "[Queue API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs)"
 */
public class Queue extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://queue.taskcluster.net/v1";

    public Queue(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Queue(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Queue(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Queue(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Queue(String baseURL) {
        super(baseURL);
    }

    public Queue() {
        super(defaultBaseURL);
    }

    /**
     * This end-point will return the task-definition. Notice that the task
     * definition may have been modified by queue, if an optional property is
     * not specified the queue may provide a default value.
     *
     * @see "[Get Task Definition API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#task)"
     */
    public CallSummary<EmptyPayload, TaskDefinitionResponse> task(String taskId) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId), TaskDefinitionResponse.class);
    }

    /**
     * Get task status structure from `taskId`
     *
     * @see "[Get task status API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#status)"
     */
    public CallSummary<EmptyPayload, TaskStatusResponse> status(String taskId) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId) + "/status", TaskStatusResponse.class);
    }

    /**
     * List tasks sharing the same `taskGroupId`.
     * 
     * As a task-group may contain an unbounded number of tasks, this end-point
     * may return a `continuationToken`. To continue listing tasks you must call
     * the `listTaskGroup` again with the `continuationToken` as the
     * query-string option `continuationToken`.
     * 
     * By default this end-point will try to return up to 1000 members in one
     * request. But it **may return less**, even if more tasks are available.
     * It may also return a `continuationToken` even though there are no more
     * results. However, you can only be sure to have seen all results if you
     * keep calling `listTaskGroup` with the last `continuationToken` until you
     * get a result without a `continuationToken`.
     * 
     * If you are not interested in listing all the members at once, you may
     * use the query-string option `limit` to return fewer.
     *
     * @see "[List Task Group API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#listTaskGroup)"
     */
    public CallSummary<EmptyPayload, ListTaskGroupResponse> listTaskGroup(String taskGroupId) throws APICallFailure {
        return apiCall(null, "GET", "/task-group/" + uriEncode(taskGroupId) + "/list", ListTaskGroupResponse.class);
    }

    /**
     * List tasks that depend on the given `taskId`.
     * 
     * As many tasks from different task-groups may dependent on a single tasks,
     * this end-point may return a `continuationToken`. To continue listing
     * tasks you must call `listDependentTasks` again with the
     * `continuationToken` as the query-string option `continuationToken`.
     * 
     * By default this end-point will try to return up to 1000 tasks in one
     * request. But it **may return less**, even if more tasks are available.
     * It may also return a `continuationToken` even though there are no more
     * results. However, you can only be sure to have seen all results if you
     * keep calling `listDependentTasks` with the last `continuationToken` until
     * you get a result without a `continuationToken`.
     * 
     * If you are not interested in listing all the tasks at once, you may
     * use the query-string option `limit` to return fewer.
     *
     * @see "[List Dependent Tasks API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#listDependentTasks)"
     */
    public CallSummary<EmptyPayload, ListDependentTasksResponse> listDependentTasks(String taskId) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId) + "/dependents", ListDependentTasksResponse.class);
    }

    /**
     * Create a new task, this is an **idempotent** operation, so repeat it if
     * you get an internal server error or network connection is dropped.
     * 
     * **Task `deadline´**, the deadline property can be no more than 5 days
     * into the future. This is to limit the amount of pending tasks not being
     * taken care of. Ideally, you should use a much shorter deadline.
     * 
     * **Task expiration**, the `expires` property must be greater than the
     * task `deadline`. If not provided it will default to `deadline` + one
     * year. Notice, that artifacts created by task must expire before the task.
     * 
     * **Task specific routing-keys**, using the `task.routes` property you may
     * define task specific routing-keys. If a task has a task specific 
     * routing-key: `<route>`, then when the AMQP message about the task is
     * published, the message will be CC'ed with the routing-key: 
     * `route.<route>`. This is useful if you want another component to listen
     * for completed tasks you have posted.  The caller must have scope
     * `queue:route:<route>` for each route.
     * 
     * **Dependencies**, any tasks referenced in `task.dependencies` must have
     * already been created at the time of this call.
     * 
     * **Important** Any scopes the task requires are also required for creating
     * the task. Please see the Request Payload (Task Definition) for details.
     *
     * Required scopes:
     *
     *   * `queue:create-task:<provisionerId>/<workerType>`, or
     *   * (`queue:define-task:<provisionerId>/<workerType>` and `queue:task-group-id:<schedulerId>/<taskGroupId>` and `queue:schedule-task:<schedulerId>/<taskGroupId>/<taskId>`)
     *
     * @see "[Create New Task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#createTask)"
     */
    public CallSummary<TaskDefinitionRequest, TaskStatusResponse> createTask(String taskId, TaskDefinitionRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/task/" + uriEncode(taskId), TaskStatusResponse.class);
    }

    /**
     * Define a task without scheduling it. This API end-point allows you to
     * upload a task definition without having scheduled. The task won't be
     * reported as pending until it is scheduled, see the scheduleTask API 
     * end-point.
     * 
     * The purpose of this API end-point is allow schedulers to upload task
     * definitions without the tasks becoming _pending_ immediately. This useful
     * if you have a set of dependent tasks. Then you can upload all the tasks
     * and when the dependencies of a tasks have been resolved, you can schedule
     * the task by calling `/task/:taskId/schedule`. This eliminates the need to
     * store tasks somewhere else while waiting for dependencies to resolve.
     * 
     * **Important** Any scopes the task requires are also required for defining
     * the task. Please see the Request Payload (Task Definition) for details.
     * 
     * **Note** this operation is **idempotent**, as long as you upload the same
     * task definition as previously defined this operation is safe to retry.
     *
     * Required scopes:
     *
     *   * `queue:define-task:<provisionerId>/<workerType>`, or
     *   * `queue:create-task:<provisionerId>/<workerType>`, or
     *   * (`queue:define-task:<provisionerId>/<workerType>` and `queue:task-group-id:<schedulerId>/<taskGroupId>`)
     *
     * @see "[Define Task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#defineTask)"
     */
    public CallSummary<TaskDefinitionRequest, TaskStatusResponse> defineTask(String taskId, TaskDefinitionRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/task/" + uriEncode(taskId) + "/define", TaskStatusResponse.class);
    }

    /**
     * scheduleTask will schedule a task to be executed, even if it has
     * unresolved dependencies. A task would otherwise only be scheduled if
     * its dependencies were resolved.
     * 
     * This is useful if you have defined a task that depends on itself or on
     * some other task that has not been resolved, but you wish the task to be
     * scheduled immediately.
     * 
     * This will announce the task as pending and workers will be allowed to
     * claim it and resolve the task.
     * 
     * **Note** this operation is **idempotent** and will not fail or complain
     * if called with a `taskId` that is already scheduled, or even resolved.
     * To reschedule a task previously resolved, use `rerunTask`.
     *
     * Required scopes:
     *
     *   * (`queue:schedule-task` and `assume:scheduler-id:<schedulerId>/<taskGroupId>`), or
     *   * `queue:schedule-task:<schedulerId>/<taskGroupId>/<taskId>`
     *
     * @see "[Schedule Defined Task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#scheduleTask)"
     */
    public CallSummary<EmptyPayload, TaskStatusResponse> scheduleTask(String taskId) throws APICallFailure {
        return apiCall(null, "POST", "/task/" + uriEncode(taskId) + "/schedule", TaskStatusResponse.class);
    }

    /**
     * This method _reruns_ a previously resolved task, even if it was
     * _completed_. This is useful if your task completes unsuccessfully, and
     * you just want to run it from scratch again. This will also reset the
     * number of `retries` allowed.
     * 
     * Remember that `retries` in the task status counts the number of runs that
     * the queue have started because the worker stopped responding, for example
     * because a spot node died.
     * 
     * **Remark** this operation is idempotent, if you try to rerun a task that
     * is not either `failed` or `completed`, this operation will just return
     * the current task status.
     *
     * Required scopes:
     *
     *   * (`queue:rerun-task` and `assume:scheduler-id:<schedulerId>/<taskGroupId>`), or
     *   * `queue:rerun-task:<schedulerId>/<taskGroupId>/<taskId>`
     *
     * @see "[Rerun a Resolved Task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#rerunTask)"
     */
    public CallSummary<EmptyPayload, TaskStatusResponse> rerunTask(String taskId) throws APICallFailure {
        return apiCall(null, "POST", "/task/" + uriEncode(taskId) + "/rerun", TaskStatusResponse.class);
    }

    /**
     * This method will cancel a task that is either `unscheduled`, `pending` or
     * `running`. It will resolve the current run as `exception` with
     * `reasonResolved` set to `canceled`. If the task isn't scheduled yet, ie.
     * it doesn't have any runs, an initial run will be added and resolved as
     * described above. Hence, after canceling a task, it cannot be scheduled
     * with `queue.scheduleTask`, but a new run can be created with
     * `queue.rerun`. These semantics is equivalent to calling
     * `queue.scheduleTask` immediately followed by `queue.cancelTask`.
     * 
     * **Remark** this operation is idempotent, if you try to cancel a task that
     * isn't `unscheduled`, `pending` or `running`, this operation will just
     * return the current task status.
     *
     * Required scopes:
     *
     *   * (`queue:cancel-task` and `assume:scheduler-id:<schedulerId>/<taskGroupId>`), or
     *   * `queue:cancel-task:<schedulerId>/<taskGroupId>/<taskId>`
     *
     * @see "[Cancel Task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#cancelTask)"
     */
    public CallSummary<EmptyPayload, TaskStatusResponse> cancelTask(String taskId) throws APICallFailure {
        return apiCall(null, "POST", "/task/" + uriEncode(taskId) + "/cancel", TaskStatusResponse.class);
    }

    /**
     * Get a signed URLs to get and delete messages from azure queue.
     * Once messages are polled from here, you can claim the referenced task
     * with `claimTask`, and afterwards you should always delete the message.
     *
     * Required scopes:
     *
     *   * (`queue:poll-task-urls` and `assume:worker-type:<provisionerId>/<workerType>`), or
     *   * `queue:poll-task-urls:<provisionerId>/<workerType>`
     *
     * @see "[Get Urls to Poll Pending Tasks API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#pollTaskUrls)"
     */
    public CallSummary<EmptyPayload, PollTaskUrlsResponse> pollTaskUrls(String provisionerId, String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/poll-task-url/" + uriEncode(provisionerId) + "/" + uriEncode(workerType), PollTaskUrlsResponse.class);
    }

    /**
     * Claim any task, more to be added later... long polling up to 20s.
     *
     * Required scopes:
     *
     *   * `queue:claim-work:<provisionerId>/<workerType>`, and
     *   * `queue:worker-id:<workerGroup>/<workerId>`
     *
     * @see "[Claim Work API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#claimWork)"
     */
    public CallSummary<ClaimWorkRequest, ClaimWorkResponse> claimWork(String provisionerId, String workerType, ClaimWorkRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/claim-work/" + uriEncode(provisionerId) + "/" + uriEncode(workerType), ClaimWorkResponse.class);
    }

    /**
     * claim a task, more to be added later...
     *
     * Required scopes:
     *
     *   * (`queue:claim-task` and `assume:worker-type:<provisionerId>/<workerType>` and `assume:worker-id:<workerGroup>/<workerId>`), or
     *   * (`queue:claim-task:<provisionerId>/<workerType>` and `queue:worker-id:<workerGroup>/<workerId>`)
     *
     * @see "[Claim Task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#claimTask)"
     */
    public CallSummary<TaskClaimRequest, TaskClaimResponse> claimTask(String taskId, String runId, TaskClaimRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/claim", TaskClaimResponse.class);
    }

    /**
     * reclaim a task more to be added later...
     *
     * Required scopes:
     *
     *   * (`queue:claim-task` and `assume:worker-id:<workerGroup>/<workerId>`), or
     *   * `queue:reclaim-task:<taskId>/<runId>`
     *
     * @see "[Reclaim task API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#reclaimTask)"
     */
    public CallSummary<EmptyPayload, TaskReclaimResponse> reclaimTask(String taskId, String runId) throws APICallFailure {
        return apiCall(null, "POST", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/reclaim", TaskReclaimResponse.class);
    }

    /**
     * Report a task completed, resolving the run as `completed`.
     *
     * Required scopes:
     *
     *   * (`queue:resolve-task` and `assume:worker-id:<workerGroup>/<workerId>`), or
     *   * `queue:resolve-task:<taskId>/<runId>`
     *
     * @see "[Report Run Completed API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#reportCompleted)"
     */
    public CallSummary<EmptyPayload, TaskStatusResponse> reportCompleted(String taskId, String runId) throws APICallFailure {
        return apiCall(null, "POST", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/completed", TaskStatusResponse.class);
    }

    /**
     * Report a run failed, resolving the run as `failed`. Use this to resolve
     * a run that failed because the task specific code behaved unexpectedly.
     * For example the task exited non-zero, or didn't produce expected output.
     * 
     * Do not use this if the task couldn't be run because if malformed
     * payload, or other unexpected condition. In these cases we have a task
     * exception, which should be reported with `reportException`.
     *
     * Required scopes:
     *
     *   * (`queue:resolve-task` and `assume:worker-id:<workerGroup>/<workerId>`), or
     *   * `queue:resolve-task:<taskId>/<runId>`
     *
     * @see "[Report Run Failed API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#reportFailed)"
     */
    public CallSummary<EmptyPayload, TaskStatusResponse> reportFailed(String taskId, String runId) throws APICallFailure {
        return apiCall(null, "POST", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/failed", TaskStatusResponse.class);
    }

    /**
     * Resolve a run as _exception_. Generally, you will want to report tasks as
     * failed instead of exception. You should `reportException` if,
     * 
     *   * The `task.payload` is invalid,
     *   * Non-existent resources are referenced,
     *   * Declared actions cannot be executed due to unavailable resources,
     *   * The worker had to shutdown prematurely,
     *   * The worker experienced an unknown error, or,
     *   * The task explicitely requested a retry.
     * 
     * Do not use this to signal that some user-specified code crashed for any
     * reason specific to this code. If user-specific code hits a resource that
     * is temporarily unavailable worker should report task _failed_.
     *
     * Required scopes:
     *
     *   * (`queue:resolve-task` and `assume:worker-id:<workerGroup>/<workerId>`), or
     *   * `queue:resolve-task:<taskId>/<runId>`
     *
     * @see "[Report Task Exception API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#reportException)"
     */
    public CallSummary<TaskExceptionRequest, TaskStatusResponse> reportException(String taskId, String runId, TaskExceptionRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/exception", TaskStatusResponse.class);
    }

    /**
     * This API end-point creates an artifact for a specific run of a task. This
     * should **only** be used by a worker currently operating on this task, or
     * from a process running within the task (ie. on the worker).
     * 
     * All artifacts must specify when they `expires`, the queue will
     * automatically take care of deleting artifacts past their
     * expiration point. This features makes it feasible to upload large
     * intermediate artifacts from data processing applications, as the
     * artifacts can be set to expire a few days later.
     * 
     * We currently support 4 different `storageType`s, each storage type have
     * slightly different features and in some cases difference semantics.
     * 
     * **S3 artifacts**, is useful for static files which will be stored on S3.
     * When creating an S3 artifact the queue will return a pre-signed URL
     * to which you can do a `PUT` request to upload your artifact. Note
     * that `PUT` request **must** specify the `content-length` header and
     * **must** give the `content-type` header the same value as in the request
     * to `createArtifact`.
     * 
     * **Azure artifacts**, are stored in _Azure Blob Storage_ service, which
     * given the consistency guarantees and API interface offered by Azure is
     * more suitable for artifacts that will be modified during the execution
     * of the task. For example docker-worker has a feature that persists the
     * task log to Azure Blob Storage every few seconds creating a somewhat
     * live log. A request to create an Azure artifact will return a URL
     * featuring a [Shared-Access-Signature](http://msdn.microsoft.com/en-us/library/azure/dn140256.aspx),
     * refer to MSDN for further information on how to use these.
     * **Warning: azure artifact is currently an experimental feature subject
     * to changes and data-drops.**
     * 
     * **Reference artifacts**, only consists of meta-data which the queue will
     * store for you. These artifacts really only have a `url` property and
     * when the artifact is requested the client will be redirect the URL
     * provided with a `303` (See Other) redirect. Please note that we cannot
     * delete artifacts you upload to other service, we can only delete the
     * reference to the artifact, when it expires.
     * 
     * **Error artifacts**, only consists of meta-data which the queue will
     * store for you. These artifacts are only meant to indicate that you the
     * worker or the task failed to generate a specific artifact, that you
     * would otherwise have uploaded. For example docker-worker will upload an
     * error artifact, if the file it was supposed to upload doesn't exists or
     * turns out to be a directory. Clients requesting an error artifact will
     * get a `403` (Forbidden) response. This is mainly designed to ensure that
     * dependent tasks can distinguish between artifacts that were suppose to
     * be generated and artifacts for which the name is misspelled.
     * 
     * **Artifact immutability**, generally speaking you cannot overwrite an
     * artifact when created. But if you repeat the request with the same
     * properties the request will succeed as the operation is idempotent.
     * This is useful if you need to refresh a signed URL while uploading.
     * Do not abuse this to overwrite artifacts created by another entity!
     * Such as worker-host overwriting artifact created by worker-code.
     * 
     * As a special case the `url` property on _reference artifacts_ can be
     * updated. You should only use this to update the `url` property for
     * reference artifacts your process has created.
     *
     * Required scopes:
     *
     *   * (`queue:create-artifact:<name>` and `assume:worker-id:<workerGroup>/<workerId>`), or
     *   * `queue:create-artifact:<taskId>/<runId>`
     *
     * @see "[Create Artifact API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#createArtifact)"
     */
    public CallSummary<Object, Object> createArtifact(String taskId, String runId, String name, Object payload) throws APICallFailure {
        return apiCall(payload, "POST", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/artifacts/" + uriEncode(name), Object.class);
    }

    /**
     * Get artifact by `<name>` from a specific run.
     * 
     * **Public Artifacts**, in-order to get an artifact you need the scope
     * `queue:get-artifact:<name>`, where `<name>` is the name of the artifact.
     * But if the artifact `name` starts with `public/`, authentication and
     * authorization is not necessary to fetch the artifact.
     * 
     * **API Clients**, this method will redirect you to the artifact, if it is
     * stored externally. Either way, the response may not be JSON. So API
     * client users might want to generate a signed URL for this end-point and
     * use that URL with a normal HTTP client.
     * 
     * **Caching**, artifacts may be cached in data centers closer to the
     * workers in-order to reduce bandwidth costs. This can lead to longer
     * response times. Caching can be skipped by setting the header
     * `x-taskcluster-skip-cache: true`, this should only be used for resources
     * where request volume is known to be low, and caching not useful.
     * (This feature may be disabled in the future, use is sparingly!)
     *
     * Required scopes:
     *
     *   * `queue:get-artifact:<name>`
     *
     * @see "[Get Artifact from Run API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#getArtifact)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> getArtifact(String taskId, String runId, String name) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/artifacts/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Get artifact by `<name>` from the last run of a task.
     * 
     * **Public Artifacts**, in-order to get an artifact you need the scope
     * `queue:get-artifact:<name>`, where `<name>` is the name of the artifact.
     * But if the artifact `name` starts with `public/`, authentication and
     * authorization is not necessary to fetch the artifact.
     * 
     * **API Clients**, this method will redirect you to the artifact, if it is
     * stored externally. Either way, the response may not be JSON. So API
     * client users might want to generate a signed URL for this end-point and
     * use that URL with a normal HTTP client.
     * 
     * **Remark**, this end-point is slightly slower than
     * `queue.getArtifact`, so consider that if you already know the `runId` of
     * the latest run. Otherwise, just us the most convenient API end-point.
     *
     * Required scopes:
     *
     *   * `queue:get-artifact:<name>`
     *
     * @see "[Get Artifact from Latest Run API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#getLatestArtifact)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> getLatestArtifact(String taskId, String name) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId) + "/artifacts/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Returns a list of artifacts and associated meta-data for a given run.
     * 
     * As a task may have many artifacts paging may be necessary. If this
     * end-point returns a `continuationToken`, you should call the end-point
     * again with the `continuationToken` as the query-string option:
     * `continuationToken`.
     * 
     * By default this end-point will list up-to 1000 artifacts in a single page
     * you may limit this with the query-string parameter `limit`.
     *
     * @see "[Get Artifacts from Run API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#listArtifacts)"
     */
    public CallSummary<EmptyPayload, ListArtifactsResponse> listArtifacts(String taskId, String runId) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId) + "/runs/" + uriEncode(runId) + "/artifacts", ListArtifactsResponse.class);
    }

    /**
     * Returns a list of artifacts and associated meta-data for the latest run
     * from the given task.
     * 
     * As a task may have many artifacts paging may be necessary. If this
     * end-point returns a `continuationToken`, you should call the end-point
     * again with the `continuationToken` as the query-string option:
     * `continuationToken`.
     * 
     * By default this end-point will list up-to 1000 artifacts in a single page
     * you may limit this with the query-string parameter `limit`.
     *
     * @see "[Get Artifacts from Latest Run API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#listLatestArtifacts)"
     */
    public CallSummary<EmptyPayload, ListArtifactsResponse> listLatestArtifacts(String taskId) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(taskId) + "/artifacts", ListArtifactsResponse.class);
    }

    /**
     * Get an approximate number of pending tasks for the given `provisionerId`
     * and `workerType`.
     * 
     * The underlying Azure Storage Queues only promises to give us an estimate.
     * Furthermore, we cache the result in memory for 20 seconds. So consumers
     * should be no means expect this to be an accurate number.
     * It is, however, a solid estimate of the number of pending tasks.
     *
     * @see "[Get Number of Pending Tasks API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#pendingTasks)"
     */
    public CallSummary<EmptyPayload, CountPendingTasksResponse> pendingTasks(String provisionerId, String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/pending/" + uriEncode(provisionerId) + "/" + uriEncode(workerType), CountPendingTasksResponse.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/platform/queue/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}