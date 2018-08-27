package org.mozilla.taskcluster.client.notify;

/**
 * Request to post a message on pulse.
 *
 * See https://schemas.taskcluster.net/notify/v1/pulse-request.json#
 */
public class PostPulseMessageRequest {

    /**
     * IRC message to send as plain text.
     *
     * See https://schemas.taskcluster.net/notify/v1/pulse-request.json#/properties/message
     */
    public Object message;

    /**
     * Routing-key to use when posting the message.
     *
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/notify/v1/pulse-request.json#/properties/routingKey
     */
    public String routingKey;
}
