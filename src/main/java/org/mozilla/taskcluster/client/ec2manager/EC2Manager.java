// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// https://references.taskcluster.net/ec2-manager/v1/api.json
package org.mozilla.taskcluster.client.ec2manager;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

/**
 * A taskcluster service which manages EC2 instances.  This service does not understand any taskcluster concepts intrinsicaly other than using the name `workerType` to refer to a group of associated instances.  Unless you are working on building a provisioner for AWS, you almost certainly do not want to use this service
 *
 * @see "[EC2Manager API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs)"
 */
public class EC2Manager extends TaskclusterRequestHandler {

    protected static final String defaultBaseURL = "localhost:5555/v1";

    public EC2Manager(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public EC2Manager(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public EC2Manager(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public EC2Manager(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public EC2Manager(String baseURL) {
        super(baseURL);
    }

    public EC2Manager() {
        super(defaultBaseURL);
    }

    /**
     * This method is only for debugging the ec2-manager
     *
     * @see "[See the list of worker types which are known to be managed API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#listWorkerTypes)"
     */
    public CallSummary<EmptyPayload, String[]> listWorkerTypes() throws APICallFailure {
        return apiCall(null, "GET", "/worker-types", String[].class);
    }

    /**
     * Request an instance of a worker type

     * Required scopes:
     *   ec2-manager:manage-resources:<workerType>
     *
     * @see "[Run an instance API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#runInstance)"
     */
    public CallSummary<MakeASpotRequest, EmptyPayload> runInstance(String workerType, MakeASpotRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/worker-types/" + uriEncode(workerType) + "/instance", EmptyPayload.class);
    }

    /**
     * Terminate all instances for this worker type

     * Required scopes:
     *   ec2-manager:manage-resources:<workerType>
     *
     * @see "[Terminate all resources from a worker type API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#terminateWorkerType)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> terminateWorkerType(String workerType) throws APICallFailure {
        return apiCall(null, "DELETE", "/worker-types/" + uriEncode(workerType) + "/resources", EmptyPayload.class);
    }

    /**
     * Return an object which has a generic state description. This only contains counts of instances
     *
     * @see "[Look up the resource stats for a workerType API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#workerTypeStats)"
     */
    public CallSummary<EmptyPayload, OverviewOfComputationalResources> workerTypeStats(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-types/" + uriEncode(workerType) + "/stats", OverviewOfComputationalResources.class);
    }

    /**
     * Return a view of the health of a given worker type
     *
     * @see "[Look up the resource health for a workerType API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#workerTypeHealth)"
     */
    public CallSummary<EmptyPayload, HealthOfTheEC2Account> workerTypeHealth(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-types/" + uriEncode(workerType) + "/health", HealthOfTheEC2Account.class);
    }

    /**
     * Return a list of the most recent errors encountered by a worker type
     *
     * @see "[Look up the most recent errors of a workerType API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#workerTypeErrors)"
     */
    public CallSummary<EmptyPayload, Errors> workerTypeErrors(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-types/" + uriEncode(workerType) + "/errors", Errors.class);
    }

    /**
     * Return state information for a given worker type
     *
     * @see "[Look up the resource state for a workerType API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#workerTypeState)"
     */
    public CallSummary<EmptyPayload, OverviewOfComputationalResources1> workerTypeState(String workerType) throws APICallFailure {
        return apiCall(null, "GET", "/worker-types/" + uriEncode(workerType) + "/state", OverviewOfComputationalResources1.class);
    }

    /**
     * Idempotently ensure that a keypair of a given name exists

     * Required scopes:
     *   ec2-manager:manage-key-pairs:<name>
     *
     * @see "[Ensure a KeyPair for a given worker type exists API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#ensureKeyPair)"
     */
    public CallSummary<SshPublicKey, EmptyPayload> ensureKeyPair(String name, SshPublicKey payload) throws APICallFailure {
        return apiCall(payload, "GET", "/key-pairs/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Ensure that a keypair of a given name does not exist.

     * Required scopes:
     *   ec2-manager:manage-key-pairs:<name>
     *
     * @see "[Ensure a KeyPair for a given worker type does not exist API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#removeKeyPair)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> removeKeyPair(String name) throws APICallFailure {
        return apiCall(null, "DELETE", "/key-pairs/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Terminate an instance in a specified region

     * Required scopes:
     *   Any of:
     *   - ec2-manager:manage-instances:<region>:<instanceId>
     *   - ec2-manager:manage-resources:<workerType>
     *
     * @see "[Terminate an instance API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#terminateInstance)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> terminateInstance(String region, String instanceId) throws APICallFailure {
        return apiCall(null, "DELETE", "/region/" + uriEncode(region) + "/instance/" + uriEncode(instanceId), EmptyPayload.class);
    }

    /**
     * Return a list of possible prices for EC2
     *
     * @see "[Request prices for EC2 API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#getPrices)"
     */
    public CallSummary<EmptyPayload, Entry1[]> getPrices() throws APICallFailure {
        return apiCall(null, "GET", "/prices", Entry1[].class);
    }

    /**
     * Return a list of possible prices for EC2
     *
     * @see "[Request prices for EC2 API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#getSpecificPrices)"
     */
    public CallSummary<Entry2[], Entry1[]> getSpecificPrices(Entry2[] payload) throws APICallFailure {
        return apiCall(payload, "POST", "/prices", Entry1[].class);
    }

    /**
     * Give some basic stats on the health of our EC2 account
     *
     * @see "[Get EC2 account health metrics API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#getHealth)"
     */
    public CallSummary<EmptyPayload, HealthOfTheEC2Account> getHealth() throws APICallFailure {
        return apiCall(null, "GET", "/health", HealthOfTheEC2Account.class);
    }

    /**
     * Return a list of recent errors encountered
     *
     * @see "[Look up the most recent errors in the provisioner across all worker types API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#getRecentErrors)"
     */
    public CallSummary<EmptyPayload, Errors> getRecentErrors() throws APICallFailure {
        return apiCall(null, "GET", "/errors", Errors.class);
    }

    /**
     * This method is only for debugging the ec2-manager

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[See the list of regions managed by this ec2-manager API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#regions)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> regions() throws APICallFailure {
        return apiCall(null, "GET", "/internal/regions", EmptyPayload.class);
    }

    /**
     * List AMIs and their usage by returning a list of objects in the form:
     * {
     * region: string
     *   volumetype: string
     *   lastused: timestamp
     * }

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[See the list of AMIs and their usage API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#amiUsage)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> amiUsage() throws APICallFailure {
        return apiCall(null, "GET", "/internal/ami-usage", EmptyPayload.class);
    }

    /**
     * Lists current EBS volume usage by returning a list of objects
     * that are uniquely defined by {region, volumetype, state} in the form:
     * {
     * region: string,
     *   volumetype: string,
     *   state: string,
     *   totalcount: integer,
     *   totalgb: integer,
     *   touched: timestamp (last time that information was updated),
     * }

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[See the current EBS volume usage list API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#ebsUsage)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ebsUsage() throws APICallFailure {
        return apiCall(null, "GET", "/internal/ebs-usage", EmptyPayload.class);
    }

    /**
     * This method is only for debugging the ec2-manager

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[Statistics on the Database client pool API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#dbpoolStats)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> dbpoolStats() throws APICallFailure {
        return apiCall(null, "GET", "/internal/db-pool-stats", EmptyPayload.class);
    }

    /**
     * This method is only for debugging the ec2-manager

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[List out the entire internal state API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#allState)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> allState() throws APICallFailure {
        return apiCall(null, "GET", "/internal/all-state", EmptyPayload.class);
    }

    /**
     * This method is only for debugging the ec2-manager

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[Statistics on the sqs queues API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#sqsStats)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> sqsStats() throws APICallFailure {
        return apiCall(null, "GET", "/internal/sqs-stats", EmptyPayload.class);
    }

    /**
     * This method is only for debugging the ec2-manager

     * Required scopes:
     *   ec2-manager:internals
     *
     * @see "[Purge the SQS queues API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#purgeQueues)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> purgeQueues() throws APICallFailure {
        return apiCall(null, "GET", "/internal/purge-queues", EmptyPayload.class);
    }

    /**
     * Generate an API reference for this service
     *
     * @see "[API Reference API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#apiReference)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> apiReference() throws APICallFailure {
        return apiCall(null, "GET", "/internal/api-reference", EmptyPayload.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/ec2-manager/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}