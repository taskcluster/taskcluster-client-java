// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/scheduler/v1/api.json
package org.mozilla.taskcluster.client.scheduler;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The task-graph scheduler, typically available at
 * `scheduler.taskcluster.net`, is responsible for accepting task-graphs and
 * scheduling tasks for evaluation by the queue as their dependencies are
 * satisfied.
 * 
 * This document describes API end-points offered by the task-graph
 * scheduler. These end-points targets the following audience:
 *  * Post-commit hooks, that wants to submit task-graphs for testing,
 *  * End-users, who wants to execute a set of dependent tasks, and
 *  * Tools, that wants to inspect the state of a task-graph.
 *
 * See: http://docs.taskcluster.net/scheduler/api-docs
 */
public class Scheduler extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://scheduler.taskcluster.net/v1";

    public Scheduler(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public Scheduler(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public Scheduler(String baseURL) {
        super(baseURL);
    }

    /**
     * Create a new task-graph, the `status` of the resulting JSON is a
     * task-graph status structure, you can find the `taskGraphId` in this
     * structure.
     * 
     * **Referencing required tasks**, it is possible to reference other tasks
     * in the task-graph that must be completed successfully before a task is
     * scheduled. You just specify the `taskId` in the list of `required` tasks.
     * See the example below, where the second task requires the first task.
     * ```js
     * {
     *   ...
     *   tasks: [
     *     {
     *       taskId:     "XgvL0qtSR92cIWpcwdGKCA",
     *       requires:   [],
     *       ...
     *     },
     *     {
     *       taskId:     "73GsfK62QNKAk2Hg1EEZTQ",
     *       requires:   ["XgvL0qtSR92cIWpcwdGKCA"],
     *       task: {
     *         payload: {
     *           env: {
     *             DEPENDS_ON:  "XgvL0qtSR92cIWpcwdGKCA"
     *           }
     *           ...
     *         }
     *         ...
     *       },
     *       ...
     *     }
     *   ]
     * }
     * ```
     * 
     * **The `schedulerId` property**, defaults to the `schedulerId` of this
     * scheduler in production that is `"task-graph-scheduler"`. This
     * property must be either undefined or set to `"task-graph-scheduler"`,
     * otherwise the task-graph will be rejected.
     * 
     * **The `taskGroupId` property**, defaults to the `taskGraphId` of the
     * task-graph submitted, and if provided much be the `taskGraphId` of
     * the task-graph. Otherwise the task-graph will be rejected.
     * 
     * **Task-graph scopes**, a task-graph is assigned a set of scopes, just
     * like tasks. Tasks within a task-graph cannot have scopes beyond those
     * the task-graph has. The task-graph scheduler will execute all requests
     * on behalf of a task-graph using the set of scopes assigned to the
     * task-graph. Thus, if you are submitting tasks to `my-worker-type` under
     * `my-provisioner` it's important that your task-graph has the scope
     * required to define tasks for this `provisionerId` and `workerType`.
     * See the queue for details on permissions required. Note, the task-graph
     * does not require permissions to schedule the tasks. This is done with
     * scopes provided by the task-graph scheduler.
     * 
     * **Task-graph specific routing-keys**, using the `taskGraph.routes`
     * property you may define task-graph specific routing-keys. If a task-graph
     * has a task-graph specific routing-key: `<route>`, then the poster will
     * be required to posses the scope `scheduler:route:<route>`. And when the
     * an AMQP message about the task-graph is published the message will be
     * CC'ed with the routing-key: `route.<route>`. This is useful if you want
     * another component to listen for completed tasks you have posted.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#createTaskGraph
     */
    public CallSummary<TaskGraphDefinition1,TaskGraphStatusResponse> createTaskGraph(String taskGraphId, TaskGraphDefinition1 payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/task-graph/" + taskGraphId + "", TaskGraphStatusResponse.class);
    }

    /**
     * Add a set of tasks to an existing task-graph. The request format is very
     * similar to the request format for creating task-graphs. But `routes`
     * key, `scopes`, `metadata` and `tags` cannot be modified.
     * 
     * **Referencing required tasks**, just as when task-graphs are created,
     * each task has a list of required tasks. It is possible to reference
     * all `taskId`s within the task-graph.
     * 
     * **Safety,** it is only _safe_ to call this API end-point while the
     * task-graph being modified is still running. If the task-graph is
     * _finished_ or _blocked_, this method will leave the task-graph in this
     * state. Hence, it is only truly _safe_ to call this API end-point from
     * within a task in the task-graph being modified.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#extendTaskGraph
     */
    public CallSummary<TaskGraphDefinition,TaskGraphStatusResponse> extendTaskGraph(String taskGraphId, TaskGraphDefinition payload) throws APICallFailure {
        return apiCall(payload, "POST", "/task-graph/" + taskGraphId + "/extend", TaskGraphStatusResponse.class);
    }

    /**
     * Get task-graph status, this will return the _task-graph status
     * structure_. which can be used to check if a task-graph is `running`,
     * `blocked` or `finished`.
     * 
     * **Note**, that `finished` implies successfully completion.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#status
     */
    public CallSummary<EmptyPayload,TaskGraphStatusResponse> status(String taskGraphId) throws APICallFailure {
        return apiCall(null, "GET", "/task-graph/" + taskGraphId + "/status", TaskGraphStatusResponse.class);
    }

    /**
     * Get task-graph information, this includes the _task-graph status
     * structure_, along with `metadata` and `tags`, but not information
     * about all tasks.
     * 
     * If you want more detailed information use the `inspectTaskGraph`
     * end-point instead.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#info
     */
    public CallSummary<EmptyPayload,TaskGraphInfoResponse> info(String taskGraphId) throws APICallFailure {
        return apiCall(null, "GET", "/task-graph/" + taskGraphId + "/info", TaskGraphInfoResponse.class);
    }

    /**
     * Inspect a task-graph, this returns all the information the task-graph
     * scheduler knows about the task-graph and the state of its tasks.
     * 
     * **Warning**, some of these fields are borderline internal to the
     * task-graph scheduler and we may choose to change or make them internal
     * later. Also note that note all of the information is formalized yet.
     * The JSON schema will be updated to reflect formalized values, we think
     * it's safe to consider the values stable.
     * 
     * Take these considerations into account when using the API end-point,
     * as we do not promise it will remain fully backward compatible in
     * the future.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#inspect
     */
    public CallSummary<EmptyPayload,InspectTaskGraphResponse> inspect(String taskGraphId) throws APICallFailure {
        return apiCall(null, "GET", "/task-graph/" + taskGraphId + "/inspect", InspectTaskGraphResponse.class);
    }

    /**
     * Inspect a task from a task-graph, this returns all the information the
     * task-graph scheduler knows about the specific task.
     * 
     * **Warning**, some of these fields are borderline internal to the
     * task-graph scheduler and we may choose to change or make them internal
     * later. Also note that note all of the information is formalized yet.
     * The JSON schema will be updated to reflect formalized values, we think
     * it's safe to consider the values stable.
     * 
     * Take these considerations into account when using the API end-point,
     * as we do not promise it will remain fully backward compatible in
     * the future.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#inspectTask
     */
    public CallSummary<EmptyPayload,InspectTaskGraphTaskResponse> inspectTask(String taskGraphId, String taskId) throws APICallFailure {
        return apiCall(null, "GET", "/task-graph/" + taskGraphId + "/inspect/" + taskId + "", InspectTaskGraphTaskResponse.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/scheduler/api-docs/#ping
     */
    public CallSummary<EmptyPayload,EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}