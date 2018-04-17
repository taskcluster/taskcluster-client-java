package org.mozilla.taskcluster.client.notify;

/**
 * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[1]
 */
public class PrivateMessage {

    /**
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[1]/properties/message
     */
    public String message;

    /**
     * User to post the message to.
     *
     * Syntax:     ^[A-Za-z\[\]\\~_\^{|}][A-Za-z0-9\-\[\]\\~_\^{|}]{0,254}$
     * Min length: 1
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/notify/v1/irc-request.json#/oneOf[1]/properties/user
     */
    public String user;
}
