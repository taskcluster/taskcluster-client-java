// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/aws-provisioner/v1/api.json
package org.mozilla.taskcluster.client.awsprovisioner;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
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
 * See: http://docs.taskcluster.net/aws-provisioner/api-docs
 */
public class AwsProvisioner extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://aws-provisioner.taskcluster.net/v1";

    public AwsProvisioner(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public AwsProvisioner(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public AwsProvisioner(String baseURL) {
        super(baseURL);
    }

    public AwsProvisioner() {
        super(defaultBaseURL);
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
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#createWorkerType
     */
    public CallSummary<CreateWorkerTypeRequest, GetWorkerTypeRequest> createWorkerType(String workerType, CreateWorkerTypeRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/worker-type/" + workerType + "", GetWorkerTypeRequest.class);
    }

    /**
     * Update a workerType and ensure that all regions have the require
     * KeyPair
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#updateWorkerType
     */
    public CallSummary<CreateWorkerTypeRequest, GetWorkerTypeRequest> updateWorkerType(String workerType, CreateWorkerTypeRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/worker-type/" + workerType + "/update", GetWorkerTypeRequest.class);
    }

    /**
     * Retreive a WorkerType definition
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#workerType
     */
    public CallSummary<EmptyPayload, GetWorkerTypeRequest> workerType(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-type/" + workerType + "", GetWorkerTypeRequest.class);
    }

    /**
     * Delete a WorkerType definition, submits requests to kill all 
     * instances and delete the KeyPair from all configured EC2 regions
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#removeWorkerType
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeWorkerType(String workerType) throws APICallFailure {
        return apiCall(null, "DELETE", "/worker-type/" + workerType + "", EmptyPayload.class);
    }

    /**
     * List all known WorkerType names
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#listWorkerTypes
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
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#createSecret
     */
    public CallSummary<GetSecretRequest, EmptyPayload> createSecret(String token, GetSecretRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/secret/" + token + "", EmptyPayload.class);
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
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#getSecret
     */
    public CallSummary<EmptyPayload, GetSecretResponse> getSecret(String token) throws APICallFailure {
        return apiCall(null, "GET", "/secret/" + token + "", GetSecretResponse.class);
    }

    /**
     * An instance will report in by giving its instance id as well
     * as its security token.  The token is given and checked to ensure
     * that it matches a real token that exists to ensure that random
     * machines do not check in.  We could generate a different token
     * but that seems like overkill
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#instanceStarted
     */
    public CallSummary<EmptyPayload, EmptyPayload> instanceStarted(String instanceId, String token) throws APICallFailure {
        return apiCall(null, "GET", "/instance-started/" + instanceId + "/" + token + "", EmptyPayload.class);
    }

    /**
     * Remove a secret.  After this call, a call to `getSecret` with the given
     * token will return no information.
     * 
     * It is very important that the consumer of a 
     * secret delete the secret from storage before handing over control
     * to untrusted processes to prevent credential and/or secret leakage.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#removeSecret
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeSecret(String token) throws APICallFailure {
        return apiCall(null, "DELETE", "/secret/" + token + "", EmptyPayload.class);
    }

    /**
     * Return the EC2 LaunchSpecifications for all combinations of regions
     * and instance types or a list of reasons why the launch specifications
     * are not valid
     * 
     * **This API end-point is experimental and may be subject to change without warning.**
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#getLaunchSpecs
     */
    public CallSummary<EmptyPayload, Object> getLaunchSpecs(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-type/" + workerType + "/launch-specifications", Object.class);
    }

    /**
     * DEPRECATED.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#awsState
     */
    public CallSummary<EmptyPayload, EmptyPayload> awsState() throws APICallFailure {
        return apiCall(null, "GET", "/aws-state", EmptyPayload.class);
    }

    /**
     * Return the state of a given workertype as stored by the provisioner. 
     * This state is stored as three lists: 1 for all instances, 1 for requests
     * which show in the ec2 api and 1 list for those only tracked internally
     * in the provisioner.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#state
     */
    public CallSummary<EmptyPayload, EmptyPayload> state(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/state/" + workerType + "", EmptyPayload.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#ping
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }

    /**
     * Get an API reference!
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#apiReference
     */
    public CallSummary<EmptyPayload, EmptyPayload> apiReference() throws APICallFailure {
        return apiCall(null, "GET", "/api-reference", EmptyPayload.class);
    }
}