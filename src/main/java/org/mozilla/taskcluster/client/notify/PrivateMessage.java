package org.mozilla.taskcluster.client.notify;

/**
 * Request to post a message on IRC.
 *
 * See https://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[1]
 */
public class PrivateMessage {

    /**
     * See https://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[1]/properties/message
     */
    public String message;

    /**
     * User to post the message to.
     *
     * Syntax:     ^[A-Za-z\[\]\\~_\^{|}][A-Za-z0-9\-\[\]\\~_\^{|}]{0,254}$
     * Min length: 1
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[1]/properties/user
     */
    public String user;
}
