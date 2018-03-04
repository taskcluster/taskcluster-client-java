package org.mozilla.taskcluster.client.authevents;

/**
 * Message reporting that a client has changed
 *
 * See http://schemas.taskcluster.net/auth/v1/client-message.json#
 */
public class ClientMessage {

    /**
     * `clientId` of the client that was changed
     *
     * Syntax:     ^[A-Za-z0-9!@/:.+|_-]+$
     *
     * See http://schemas.taskcluster.net/auth/v1/client-message.json#/properties/clientId
     */
    public String clientId;

    /**
     * Message version number
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/auth/v1/client-message.json#/properties/version
     */
    public Object version;
}
