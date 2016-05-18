package org.mozilla.taskcluster.client.authevents;

/**
* Message reporting that a client has changed
*
* See http://schemas.taskcluster.net/auth/v1/client-message.json#
*/
public class ClientMessage {

    /**
     * `clientId` of the client that was changed
     */
    public String clientId;

    /**
     * Message version number
     */
    public Object version;
}