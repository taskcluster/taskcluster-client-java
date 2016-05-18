// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/aws-provisioner/v1/api.json
package org.mozilla.taskcluster.client.awsprovisioner;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The AWS Provisioner is responsible for provisioning instances on EC2 for use in
 * TaskCluster.  The provisioner maintains a set of worker configurations which
 * can be managed with an API that is typically available at
 * aws-provisioner.taskcluster.net/v1.  This API can also perform basic instance
 * management tasks in addition to maintaining the internal state of worker type
 * configuration information.
 * 
 * The Provisioner runs at a configurable interval.  Each iteration of the
 * provisioner fetches a current copy the state that the AWS EC2 api reports.  In
 * each iteration, we ask the Queue how many tasks are pending for that worker
 * type.  Based on the number of tasks pending and the scaling ratio, we may
 * submit requests for new instances.  We use pricing information, capacity and
 * utility factor information to decide which instance type in which region would
 * be the optimal configuration.
 * 
 * Each EC2 instance type will declare a capacity and utility factor.  Capacity is
 * the number of tasks that a given machine is capable of running concurrently.
 * Utility factor is a relative measure of performance between two instance types.
 * We multiply the utility factor by the spot price to compare instance types and
 * regions when making the bidding choices.
 * 
 * When a new EC2 instance is instantiated, its user data contains a token in
 * `securityToken` that can be used with the `getSecret` method to retrieve
 * the worker's credentials and any needed passwords or other restricted
 * information.  The worker is responsible for deleting the secret after
 * retrieving it, to prevent dissemination of the secret to other proceses
 * which can read the instance user data.
 *
 * See: https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs
 */
public class AwsProvisioner extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://aws-provisioner.taskcluster.net/v1";

    public AwsProvisioner(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public AwsProvisioner(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public AwsProvisioner(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public AwsProvisioner(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public AwsProvisioner(String baseURL) {
        super(baseURL);
    }

    public AwsProvisioner() {
        super(defaultBaseURL);
    }

    /**
     * Return a list of worker types, including some summary information about
     * current capacity for each.  While this list includes all defined worker types,
     * there may be running EC2 instances for deleted worker types that are not
     * included here.  The list is unordered.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#listWorkerTypeSummaries
     */
    public CallSummary<EmptyPayload, WorkerTypeSummary[]> listWorkerTypeSummaries() throws APICallFailure {
        return apiCall(null, "GET", "/list-worker-type-summaries", WorkerTypeSummary[].class);
    }

    /**
     * Create a worker type.  A worker type contains all the configuration
     * needed for the provisioner to manage the instances.  Each worker type
     * knows which regions and which instance types are allowed for that
     * worker type.  Remember that Capacity is the number of concurrent tasks
     * that can be run on a given EC2 resource and that Utility is the relative
     * performance rate between different instance types.  There is no way to
     * configure different regions to have different sets of instance types
     * so ensure that all instance types are available in all regions.
     * This function is idempotent.
     * 
     * Once a worker type is in the provisioner, a back ground process will
     * begin creating instances for it based on its capacity bounds and its
     * pending task count from the Queue.  It is the worker's responsibility
     * to shut itself down.  The provisioner has a limit (currently 96hours)
     * for all instances to prevent zombie instances from running indefinitely.
     * 
     * The provisioner will ensure that all instances created are tagged with
     * aws resource tags containing the provisioner id and the worker type.
     * 
     * If provided, the secrets in the global, region and instance type sections
     * are available using the secrets api.  If specified, the scopes provided
     * will be used to generate a set of temporary credentials available with
     * the other secrets.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#createWorkerType
     */
    public CallSummary<CreateWorkerTypeRequest, GetWorkerTypeResponse> createWorkerType(String workerType, CreateWorkerTypeRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/worker-type/" + uriEncode(workerType), GetWorkerTypeResponse.class);
    }

    /**
     * Provide a new copy of a worker type to replace the existing one.
     * This will overwrite the existing worker type definition if there
     * is already a worker type of that name.  This method will return a
     * 200 response along with a copy of the worker type definition created
     * Note that if you are using the result of a GET on the worker-type
     * end point that you will need to delete the lastModified and workerType
     * keys from the object returned, since those fields are not allowed
     * the request body for this method
     * 
     * Otherwise, all input requirements and actions are the same as the
     * create method.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#updateWorkerType
     */
    public CallSummary<CreateWorkerTypeRequest, GetWorkerTypeResponse> updateWorkerType(String workerType, CreateWorkerTypeRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/worker-type/" + uriEncode(workerType) + "/update", GetWorkerTypeResponse.class);
    }

    /**
     * Retreive a copy of the requested worker type definition.
     * This copy contains a lastModified field as well as the worker
     * type name.  As such, it will require manipulation to be able to
     * use the results of this method to submit date to the update
     * method.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#workerType
     */
    public CallSummary<EmptyPayload, GetWorkerTypeResponse> workerType(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-type/" + uriEncode(workerType), GetWorkerTypeResponse.class);
    }

    /**
     * Delete a worker type definition.  This method will only delete
     * the worker type definition from the storage table.  The actual
     * deletion will be handled by a background worker.  As soon as this
     * method is called for a worker type, the background worker will
     * immediately submit requests to cancel all spot requests for this
     * worker type as well as killing all instances regardless of their
     * state.  If you want to gracefully remove a worker type, you must
     * either ensure that no tasks are created with that worker type name
     * or you could theoretically set maxCapacity to 0, though, this is
     * not a supported or tested action
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#removeWorkerType
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeWorkerType(String workerType) throws APICallFailure {
        return apiCall(null, "DELETE", "/worker-type/" + uriEncode(workerType), EmptyPayload.class);
    }

    /**
     * Return a list of string worker type names.  These are the names
     * of all managed worker types known to the provisioner.  This does
     * not include worker types which are left overs from a deleted worker
     * type definition but are still running in AWS.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#listWorkerTypes
     */
    public CallSummary<EmptyPayload, String[]> listWorkerTypes() throws APICallFailure {
        return apiCall(null, "GET", "/list-worker-types", String[].class);
    }

    /**
     * Insert a secret into the secret storage.  The supplied secrets will
     * be provided verbatime via `getSecret`, while the supplied scopes will
     * be converted into credentials by `getSecret`.
     * 
     * This method is not ordinarily used in production; instead, the provisioner
     * creates a new secret directly for each spot bid.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#createSecret
     */
    public CallSummary<GetSecretRequest, EmptyPayload> createSecret(String token, GetSecretRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/secret/" + uriEncode(token), EmptyPayload.class);
    }

    /**
     * Retrieve a secret from storage.  The result contains any passwords or
     * other restricted information verbatim as well as a temporary credential
     * based on the scopes specified when the secret was created.
     * 
     * It is important that this secret is deleted by the consumer (`removeSecret`),
     * or else the secrets will be visible to any process which can access the
     * user data associated with the instance.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#getSecret
     */
    public CallSummary<EmptyPayload, GetSecretResponse> getSecret(String token) throws APICallFailure {
        return apiCall(null, "GET", "/secret/" + uriEncode(token), GetSecretResponse.class);
    }

    /**
     * An instance will report in by giving its instance id as well
     * as its security token.  The token is given and checked to ensure
     * that it matches a real token that exists to ensure that random
     * machines do not check in.  We could generate a different token
     * but that seems like overkill
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#instanceStarted
     */
    public CallSummary<EmptyPayload, EmptyPayload> instanceStarted(String instanceId, String token) throws APICallFailure {
        return apiCall(null, "GET", "/instance-started/" + uriEncode(instanceId) + "/" + uriEncode(token), EmptyPayload.class);
    }

    /**
     * Remove a secret.  After this call, a call to `getSecret` with the given
     * token will return no information.
     * 
     * It is very important that the consumer of a 
     * secret delete the secret from storage before handing over control
     * to untrusted processes to prevent credential and/or secret leakage.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#removeSecret
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeSecret(String token) throws APICallFailure {
        return apiCall(null, "DELETE", "/secret/" + uriEncode(token), EmptyPayload.class);
    }

    /**
     * This method returns a preview of all possible launch specifications
     * that this worker type definition could submit to EC2.  It is used to
     * test worker types, nothing more
     * 
     * **This API end-point is experimental and may be subject to change without warning.**
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#getLaunchSpecs
     */
    public CallSummary<EmptyPayload, Object> getLaunchSpecs(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-type/" + uriEncode(workerType) + "/launch-specifications", Object.class);
    }

    /**
     * Return the state of a given workertype as stored by the provisioner. 
     * This state is stored as three lists: 1 for all instances, 1 for requests
     * which show in the ec2 api and 1 list for those only tracked internally
     * in the provisioner.  The `summary` property contains an updated summary
     * similar to that returned from `listWorkerTypeSummaries`.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#state
     */
    public CallSummary<EmptyPayload, EmptyPayload> state(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/state/" + uriEncode(workerType), EmptyPayload.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }

    /**
     * This endpoint is used to show when the last time the provisioner
     * has checked in.  A check in is done through the deadman's snitch
     * api.  It is done at the conclusion of a provisioning iteration
     * and used to tell if the background provisioning process is still
     * running.
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See https://docs.taskcluster.net/reference/core/aws-provisioner/api-docs/#backendStatus
     */
    public CallSummary<EmptyPayload, BackendStatusResponse> backendStatus() throws APICallFailure {
        return apiCall(null, "GET", "/backend-status", BackendStatusResponse.class);
    }
}