package org.mozilla.taskcluster.client.queue;

/**
 * See taskcluster [actions](https://docs.taskcluster.net/reference/platform/taskcluster-queue/docs/actions) documentation.
 *
 * See http://schemas.taskcluster.net/queue/v1/actions.json#
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
