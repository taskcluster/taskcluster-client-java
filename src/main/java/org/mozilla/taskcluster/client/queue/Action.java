package org.mozilla.taskcluster.client.queue;

/**
 * Actions provide a generic mechanism to expose additional features of a
 * provisioner, worker type, or worker to Taskcluster clients.
 *
 * An action is comprised of metadata describing the feature it exposes,
 * together with a webhook for triggering it.
 *
 * The Taskcluster tools site, for example, retrieves actions when displaying
 * provisioners, worker types and workers. It presents the provisioner/worker
 * type/worker specific actions to the user. When the user triggers an action,
 * the web client takes the registered webhook, substitutes parameters into the
 * URL (see `url`), signs the requests with the Taskcluster credentials of the
 * user operating the web interface, and issues the HTTP request.
 *
 * The level to which the action relates (provisioner, worker type, worker) is
 * called the action context. All actions, regardless of the action contexts,
 * are registered against the provisioner when calling
 * `queue.declareProvisioner`.
 *
 * The action context is used by the web client to determine where in the web
 * interface to present the action to the user as follows:
 *
 * | `context`   | Tool where action is displayed |
 * |-------------|--------------------------------|
 * | provisioner | Provisioner Explorer           |
 * | worker-type | Workers Explorer               |
 * | worker      | Worker Explorer                |
 *
 * See [actions docs](https://docs.taskcluster.net/reference/platform/taskcluster-queue/docs/actions)
 * for more information.
 *
 * See http://schemas.taskcluster.net/queue/v1/actions.json#/items
 */
public class Action {

    /**
     * Actions have a "context" that is one of provisioner, worker-type, or worker, indicating
     * which it applies to. `context` is used by the front-end to know where to display the action.
     *
     * | `context`   | Page displayed        |
     * |-------------|-----------------------|
     * | provisioner | Provisioner Explorer  |
     * | worker-type | Workers Explorer      |
     * | worker      | Worker Explorer       |
     *
     * Possible values:
     *     * "provisioner"
     *     * "worker-type"
     *     * "worker"
     *
     * See http://schemas.taskcluster.net/queue/v1/actions.json#/items/properties/context
     */
    public String context;

    /**
     * Description of the provisioner.
     *
     * See http://schemas.taskcluster.net/queue/v1/actions.json#/items/properties/description
     */
    public String description;

    /**
     * Method to indicate the desired action to be performed for a given resource.
     *
     * Possible values:
     *     * "POST"
     *     * "PUT"
     *     * "DELETE"
     *     * "PATCH"
     *
     * See http://schemas.taskcluster.net/queue/v1/actions.json#/items/properties/method
     */
    public String method;

    /**
     * Short names for things like logging/error messages.
     *
     * See http://schemas.taskcluster.net/queue/v1/actions.json#/items/properties/name
     */
    public String name;

    /**
     * Appropriate title for any sort of Modal prompt.
     *
     * See http://schemas.taskcluster.net/queue/v1/actions.json#/items/properties/title
     */
    public Object title;

    /**
     * When an action is triggered, a request is made using the `url` and `method`.
     * Depending on the `context`, the following parameters will be substituted in the url:
     *
     * | `context`   | Path parameters                                          |
     * |-------------|----------------------------------------------------------|
     * | provisioner | <provisionerId>                                          |
     * | worker-type | <provisionerId>, <workerType>                            |
     * | worker      | <provisionerId>, <workerType>, <workerGroup>, <workerId> |
     *
     * _Note: The request needs to be signed with the user's Taskcluster credentials._
     *
     * See http://schemas.taskcluster.net/queue/v1/actions.json#/items/properties/url
     */
    public String url;
}
