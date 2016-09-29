package org.mozilla.taskcluster.client.pulse;

/**
 * Rabbit overview response
 *
 * See http://schemas.taskcluster.net/pulse/v1/rabbit-overview.json#
 */
public class RabbitOverviewResponse {

    /**
     * The name of the cluster
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/pulse/v1/rabbit-overview.json#/properties/cluster_name
     */
    public String cluster_name;

    /**
     * The version of the management
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/pulse/v1/rabbit-overview.json#/properties/management_version
     */
    public String management_version;

    /**
     * The version of RabbitMQ
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/pulse/v1/rabbit-overview.json#/properties/rabbitmq_version
     */
    public String rabbitmq_version;
}
