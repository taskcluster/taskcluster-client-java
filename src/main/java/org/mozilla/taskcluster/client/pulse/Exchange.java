package org.mozilla.taskcluster.client.pulse;

/**
 * An array of RabbitMQ exchanges containing the details of RabbitMQ exchanges
 *
 * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#
 */
public class Exchange {

    public class Arguments {
    }

    /**
     * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#/items/properties/arguments
     */
    public Arguments arguments;

    /**
     * Whether or not the exchange deletes when all queues are finished using it
     *
     * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#/items/properties/auto-delete
     */
    public boolean autodelete;

    /**
     * Whether or not the exchange survives broker restart
     *
     * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#/items/properties/durable
     */
    public boolean durable;

    /**
     * The exchange's name
     *
     * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#/items/properties/name
     */
    public String name;

    /**
     * The exchange's type
     *
     * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#/items/properties/type
     */
    public String type;

    /**
     * The exchange's vhost
     *
     * See http://schemas.taskcluster.net/pulse/v1/exchanges-response.json#/items/properties/vhost
     */
    public String vhost;
}
