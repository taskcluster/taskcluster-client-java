package org.mozilla.taskcluster.client.notify;

/**
 * Request to post a message on IRC.
 *
 * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[0]
 */
public class ChannelMessage {

    /**
     * Channel to post the message in.
     *
     * Syntax:     ^[#&][^ ,\u0007]{1,199}$
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[0]/properties/channel
     */
    public String channel;

    /**
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[0]/properties/message
     */
    public String message;
}
