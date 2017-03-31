package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

/**
 * Representation of the namespace
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace.json#
 */
public class Namespace {

    /**
     * The contact information which will be handed off to the notification service
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace.json#/properties/contact
     */
    public Object contact;

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
