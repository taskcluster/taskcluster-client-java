package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

/**
 * Namespace creation request
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#
 */
public class NamespaceCreationRequest {

    /**
     * The contact information which will be handed off to the notification service
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/contact
     */
    public Object contact;

    /**
     * Date-time after which the username, and all associated queues and
     * exchanges, should be deleted. This can be updated on every claim, with no
     * limit. In most cases it should be set to several days in the future.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/expires
     */
    public Date expires;
}
