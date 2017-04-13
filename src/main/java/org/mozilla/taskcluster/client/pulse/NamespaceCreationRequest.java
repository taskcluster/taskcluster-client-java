package org.mozilla.taskcluster.client.pulse;

import java.util.Date;

/**
 * Namespace creation request
 *
 * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#
 */
public class NamespaceCreationRequest {

    /**
     * E-mail address that will reach people who can address problems with runaway queue length.
     * The service will send warning notifications to this address before forcibly deleting the
     * queue.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/contact
     */
    public String contact;

    /**
     * Date-time after which the username, and all associated queues and
     * exchanges, should be deleted. This can be updated on every claim, with no
     * limit. In most cases it should be set to several days in the future.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/expires
     */
    public Date expires;
}
