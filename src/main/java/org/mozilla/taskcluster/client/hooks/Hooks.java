// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/hooks/v1/api.json
package org.mozilla.taskcluster.client.hooks;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
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
 * @see "[Hooks API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs)"
 */
public class Hooks extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://hooks.taskcluster.net/v1";

    public Hooks(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Hooks(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Hooks(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Hooks(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
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
     * @see "[List hook groups API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#listHookGroups)"
     */
    public CallSummary<EmptyPayload, HookGroups> listHookGroups() throws APICallFailure {
        return apiCall(null, "GET", "/hooks", HookGroups.class);
    }

    /**
     * This endpoint will return a list of all the hook definitions within a
     * given hook group.
     *
     * @see "[List hooks in a given group API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#listHooks)"
     */
    public CallSummary<EmptyPayload, HookList> listHooks(String hookGroupId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId), HookList.class);
    }

    /**
     * This endpoint will return the hook defintion for the given `hookGroupId`
     * and hookId.
     *
     * @see "[Get hook definition API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#hook)"
     */
    public CallSummary<EmptyPayload, HookDefinition> hook(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), HookDefinition.class);
    }

    /**
     * This endpoint will return the current status of the hook.  This represents a
     * snapshot in time and may vary from one call to the next.
     *
     * @see "[Get hook status API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#getHookStatus)"
     */
    public CallSummary<EmptyPayload, HookStatusResponse> getHookStatus(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/status", HookStatusResponse.class);
    }

    /**
     * This endpoint will return the schedule and next scheduled creation time
     * for the given hook.
     *
     * @see "[Get hook schedule API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#getHookSchedule)"
     */
    public CallSummary<EmptyPayload, HookScheduleResponse> getHookSchedule(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/schedule", HookScheduleResponse.class);
    }

    /**
     * This endpoint will create a new hook.
     * 
     * The caller's credentials must include the role that will be used to
     * create the task.  That role must satisfy task.scopes as well as the
     * necessary scopes to add the task to the queue.
     *
     * Required scopes:
     *
     *   * `hooks:modify-hook:<hookGroupId>/<hookId>`, and
     *   * `assume:hook-id:<hookGroupId>/<hookId>`
     *
     * @see "[Create a hook API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#createHook)"
     */
    public CallSummary<HookCreationRequest, HookDefinition> createHook(String hookGroupId, String hookId, HookCreationRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), HookDefinition.class);
    }

    /**
     * This endpoint will update an existing hook.  All fields except
     * `hookGroupId` and `hookId` can be modified.
     *
     * Required scopes:
     *
     *   * `hooks:modify-hook:<hookGroupId>/<hookId>`, and
     *   * `assume:hook-id:<hookGroupId>/<hookId>`
     *
     * @see "[Update a hook API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#updateHook)"
     */
    public CallSummary<HookCreationRequest, HookDefinition> updateHook(String hookGroupId, String hookId, HookCreationRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), HookDefinition.class);
    }

    /**
     * This endpoint will remove a hook definition.
     *
     * Required scopes:
     *
     *   * `hooks:modify-hook:<hookGroupId>/<hookId>`
     *
     * @see "[Delete a hook API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#removeHook)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeHook(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "DELETE", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId), EmptyPayload.class);
    }

    /**
     * This endpoint will trigger the creation of a task from a hook definition.
     *
     * Required scopes:
     *
     *   * `hooks:trigger-hook:<hookGroupId>/<hookId>`
     *
     * @see "[Trigger a hook API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#triggerHook)"
     */
    public CallSummary<Object, TaskStatusStructure> triggerHook(String hookGroupId, String hookId, Object payload) throws APICallFailure {
        return apiCall(payload, "POST", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/trigger", TaskStatusStructure.class);
    }

    /**
     * Retrieve a unique secret token for triggering the specified hook. This
     * token can be deactivated with `resetTriggerToken`.
     *
     * Required scopes:
     *
     *   * `hooks:get-trigger-token:<hookGroupId>/<hookId>`
     *
     * @see "[Get a trigger token API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#getTriggerToken)"
     */
    public CallSummary<EmptyPayload, TriggerTokenResponse> getTriggerToken(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "GET", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/token", TriggerTokenResponse.class);
    }

    /**
     * Reset the token for triggering a given hook. This invalidates token that
     * may have been issued via getTriggerToken with a new token.
     *
     * Required scopes:
     *
     *   * `hooks:reset-trigger-token:<hookGroupId>/<hookId>`
     *
     * @see "[Reset a trigger token API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#resetTriggerToken)"
     */
    public CallSummary<EmptyPayload, TriggerTokenResponse> resetTriggerToken(String hookGroupId, String hookId) throws APICallFailure {
        return apiCall(null, "POST", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/token", TriggerTokenResponse.class);
    }

    /**
     * This endpoint triggers a defined hook with a valid token.
     *
     * @see "[Trigger a hook with a token API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#triggerHookWithToken)"
     */
    public CallSummary<Object, TaskStatusStructure> triggerHookWithToken(String hookGroupId, String hookId, String token, Object payload) throws APICallFailure {
        return apiCall(payload, "POST", "/hooks/" + uriEncode(hookGroupId) + "/" + uriEncode(hookId) + "/trigger/" + uriEncode(token), TaskStatusStructure.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/hooks/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}