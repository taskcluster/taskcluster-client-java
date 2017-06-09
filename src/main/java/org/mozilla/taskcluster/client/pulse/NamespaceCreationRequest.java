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
     * queue.  If omitted, no warnings will be given.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/contact
     */
    public String contact;

    /**
     * Date-time after which the username, and all associated queues and
     * exchanges, should be deleted. This can be updated on every claim, with no
     * limit.  This should be set to a very short time for temporary credentials
     * (such as for tests), and for a long time (days) for production credentials.
     * It defaults to four hours.
     *
     * See http://schemas.taskcluster.net/pulse/v1/namespace-request.json#/properties/expires
     */
    public Date expires;
}
