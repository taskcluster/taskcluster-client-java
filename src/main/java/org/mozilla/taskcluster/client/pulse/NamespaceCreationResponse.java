package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

/**
 * Namespace creation response
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#
 */
public class NamespaceCreationResponse {

    /**
     * The AMQP URL for connecting to the pulse service.  Note that this URL
     * contains a password, so it should not be logged or displayed to users.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/connectionString
     */
    public String connectionString;

    /**
     * E-mail address that will reach people who can address problems with runaway queue length.
     * The service will send warning notifications to this address before forcibly deleting the
     * queue.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/contact
     */
    public String contact;

    /**
     * Date-time at which this namespace was first claimed.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/created
     */
    public Date created;

    /**
     * Date-time after which the username, and all associated queues and
     * exchanges, should be deleted.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/expires
     */
    public Date expires;

    /**
     * The name of the namespace created
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/namespace
     */
    public String namespace;

    /**
     * The caller should plan to call `claimNamespace` again at this time. The provided
     * password will become invalid a short time after this.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-response.json#/properties/reclaimAt
     */
    public Date reclaimAt;
}
