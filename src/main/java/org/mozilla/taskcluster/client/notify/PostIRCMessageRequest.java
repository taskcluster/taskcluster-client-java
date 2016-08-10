package org.mozilla.taskcluster.client.notify;

/**
 * Request to post a message on IRC.
 *
 * See http://schemas.taskcluster.net/notify/v1/irc-request.json#
 */
public class PostIRCMessageRequest {

    /**
     * Channel to post the message in. Please note that you **must** supply
     * either `user` or `channel`, you cannot supply both.
     *
     * Syntax:     ^[#&][^ ,\u0007]{1,199}$
     *
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/properties/channel
     */
    public String channel;

    /**
     * IRC message to send as plain text.
     *
     * Min length: 1
     * Max length: 510
     *
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/properties/message
     */
    public String message;

    /**
     * User to post the message to. Please note that you **must** supply
     * either `user` or `channel`, you cannot supply both.
     *
     * Min length: 1
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/properties/user
     */
    public String user;
}
