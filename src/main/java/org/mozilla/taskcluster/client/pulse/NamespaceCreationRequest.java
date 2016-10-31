package org.mozilla.taskcluster.client.pulse;

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
}
