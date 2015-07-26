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
 * aws-provisioner.taskcluster.net.  This API can also perform basic instance
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
 * See: http://docs.taskcluster.net/aws-provisioner/api-docs
 */
public class AwsProvisioner extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://taskcluster-aws-provisioner2.herokuapp.com/v1";

    public AwsProvisioner(String clientId, String accessToken) {
        super(clientId, accessToken, defaultBaseURL);
    }

    public AwsProvisioner(String clientId, String accessToken, String certificate) {
        super(clientId, accessToken, certificate, defaultBaseURL);
    }

    public AwsProvisioner(String baseURL) {
        super(baseURL);
    }

    /**
     * Create a worker type and ensure that all EC2 regions have the required
     * KeyPair
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#createWorkerType
     */
    public CallSummary<CreateWorkerTypeRequest,GetWorkerTypeRequest> createWorkerType(String workerType, CreateWorkerTypeRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/worker-type/" + workerType + "", GetWorkerTypeRequest.class);
    }

    /**
     * Update a workerType and ensure that all regions have the require
     * KeyPair
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#updateWorkerType
     */
    public CallSummary<CreateWorkerTypeRequest,GetWorkerTypeRequest> updateWorkerType(String workerType, CreateWorkerTypeRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/worker-type/" + workerType + "/update", GetWorkerTypeRequest.class);
    }

    /**
     * Retreive a WorkerType definition
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#workerType
     */
    public CallSummary<EmptyPayload,GetWorkerTypeRequest> workerType(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-type/" + workerType + "", GetWorkerTypeRequest.class);
    }

    /**
     * Delete a WorkerType definition, submits requests to kill all 
     * instances and delete the KeyPair from all configured EC2 regions
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#removeWorkerType
     */
    public CallSummary<EmptyPayload,EmptyPayload> removeWorkerType(String workerType) throws APICallFailure {
        return apiCall(null, "DELETE", "/worker-type/" + workerType + "", EmptyPayload.class);
    }

    /**
     * List all known WorkerType names
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#listWorkerTypes
     */
    public CallSummary<EmptyPayload,String[]> listWorkerTypes() throws APICallFailure {
        return apiCall(null, "GET", "/list-worker-types", String[].class);
    }

    /**
     * Insert a secret into the secret storage.  This should not
     * normally be done through this API, but is provided for testing
     * and completeness
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#createSecret
     */
    public CallSummary<GetSecretRequest,EmptyPayload> createSecret(String token, GetSecretRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/secret/" + token + "", EmptyPayload.class);
    }

    /**
     * Retreive a secret from storage.  It is important that this secret is
     * deleted by the consumer, or else the secrets will be visible to any
     * process which can read HTTP on the worker localhost interface.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#getSecret
     */
    public CallSummary<EmptyPayload,GetSecretResponse> getSecret(String token) throws APICallFailure {
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
    public CallSummary<EmptyPayload,EmptyPayload> instanceStarted(String instanceId, String token) throws APICallFailure {
        return apiCall(null, "GET", "/instance-started/" + instanceId + "/" + token + "", EmptyPayload.class);
    }

    /**
     * Remove a secret.  It is very important that the consumer of a 
     * secret delete the secret from storage before handing over control
     * to another process or else it could read the HTTP UserData endpoint
     * and use the getSecrete() api here to get the secrets
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#removeSecret
     */
    public CallSummary<EmptyPayload,EmptyPayload> removeSecret(String token) throws APICallFailure {
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
    public CallSummary<EmptyPayload,Object> getLaunchSpecs(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-type/" + workerType + "/launch-specifications", Object.class);
    }

    /**
     * DEPRECATED.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#awsState
     */
    public CallSummary<EmptyPayload,EmptyPayload> awsState() throws APICallFailure {
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
    public CallSummary<EmptyPayload,EmptyPayload> state(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/state/" + workerType + "", EmptyPayload.class);
    }

    /**
     * Documented later...
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#ping
     */
    public CallSummary<EmptyPayload,EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }

    /**
     * Get an API reference!
     * 
     * **Warning** this api end-point is **not stable**.
     *
     * See http://docs.taskcluster.net/aws-provisioner/api-docs/#apiReference
     */
    public CallSummary<EmptyPayload,EmptyPayload> apiReference() throws APICallFailure {
        return apiCall(null, "GET", "/api-reference", EmptyPayload.class);
    }
}