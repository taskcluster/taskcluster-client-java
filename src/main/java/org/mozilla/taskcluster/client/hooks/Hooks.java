// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/hooks/v1/api.json
package org.mozilla.taskcluster.client.hooks;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * Hooks are a mechanism for creating tasks in response to events.
 * 
 * Hooks are identified with a `hookGroupId` and a `hookId`.
 * 
 * When an event occurs, the resulting task is automatically created.  The
 * task is created using the scope `assume:hook-id:<hookGroupId>/<hookId>`,
 * which must have scopes to make the createTask call, including satisfying all
 * scopes in `task.scopes`.
 * 
 * Hooks can have a 'schedule' indicating specific times that new tasks should
 * be created.  Each schedule is in a simple cron format, per 
 * https://www.npmjs.com/package/cron-parser.  For example:
 *  * `["0 0 1 * * *"]` -- daily at 1:00 UTC
 *  * `["0 0 9,21 * * 1-5", "0 0 12 * * 0,6"]` -- weekdays at 9:00 and 21:00 UTC, weekends at noon
 *
 * See: http://docs.taskcluster.net/services/hooks
 */
public class Hooks extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://hooks.taskcluster.net/v1";

    public Hooks(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public Hooks(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public Hooks(String baseURL) {
        super(baseURL);
    }

    public Hooks() {
        super(defaultBaseURL);
    }

    /**
     * This endpoint will return a list of all hook groups with at least one hook.
     *
     * See http://docs.taskcluster.net/services/hooks/#listHookGroups
     */
    public CallSummary<EmptyPayload, HookGroups> listHookGroups() throws APICallFailure {
        return apiCall(null, "GET", "/hooks", HookGroups.class);
    }

    /**
     * This endpoint will return a list of all the hook definitions within a
     * given hook group.
     *
     * See http://docs.taskcluster.net/services/hooks/#listHooks
     */
    public CallSummary<EmptyPayload, HookList> listHooks(String hookGroupId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId), HookList.class);
    }

    /**
     * This endpoint will return the hook defintion for the given `hookGroupId`
     * and hookId.
     *
     * See http://docs.taskcluster.net/services/hooks/#hook
     */
    public CallSummary<EmptyPayload, HookDefinition> hook(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), HookDefinition.class);
    }

    /**
     * This endpoint will return the schedule and next scheduled creation time
     * for the given hook.
     *
     * See http://docs.taskcluster.net/services/hooks/#getHookSchedule
     */
    public CallSummary<EmptyPayload, HookSchedule> getHookSchedule(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/schedule", HookSchedule.class);
    }

    /**
     * This endpoint will create a new hook.
     * 
     * The caller's credentials must include the role that will be used to
     * create the task.  That role must satisfy task.scopes as well as the
     * necessary scopes to add the task to the queue.
     *
     * See http://docs.taskcluster.net/services/hooks/#createHook
     */
    public CallSummary<HookCreationRequest, HookDefinition> createHook(String hookGroupId, String hookId, HookCreationRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), HookDefinition.class);
    }

    /**
     * This endpoint will update an existing hook.  All fields except
     * `hookGroupId` and `hookId` can be modified.
     *
     * See http://docs.taskcluster.net/services/hooks/#updateHook
     */
    public CallSummary<HookCreationRequest, HookDefinition> updateHook(String hookGroupId, String hookId, HookCreationRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), HookDefinition.class);
    }

    /**
     * This endpoint will remove a hook definition.
     *
     * See http://docs.taskcluster.net/services/hooks/#removeHook
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeHook(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "DELETE", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), EmptyPayload.class);
    }
}