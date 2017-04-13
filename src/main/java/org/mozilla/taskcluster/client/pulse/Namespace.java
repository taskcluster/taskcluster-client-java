package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

/**
 * Representation of the namespace
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace.json#
 */
public class Namespace {

    /**
     * E-mail address that will reach people who can address problems with runaway queue length.
     * The service will send warning notifications to this address before forcibly deleting the
     * queue.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace.json#/properties/contact
     */
    public String contact;

    /**
     * Date-time at which this namespace was first claimed.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace.json#/properties/created
     */
    public Date created;

    /**
     * Date-time after which the username, and all associated queues and
     * exchanges, should be deleted.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace.json#/properties/expires
     */
    public Date expires;

    /**
     * The namespace's name
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace.json#/properties/namespace
     */
    public String namespace;
}
