package org.mozilla.taskcluster.client.pulse;

/**
 * Request to post a message on IRC.
 *
 * See http://schemas.taskcluster.net/pulse/v1/irc-request.json#
 */
public class PostIRCMessageRequest {

    /**
     * The contact method (eg. irc)
     *
     * Possible values:
     *     * "irc"
     *
     * See http://schemas.taskcluster.net/pulse/v1/irc-request.json#/properties/method
     */
    public String method;

    public class Payload {

        /**
         * Channel to post the message in. Please note that you **must** supply
         * either `user` or `channel`, you cannot supply both.
         *
         * Syntax:     ^[#&][^ ,\u0007]{1,199}$
         *
         * See http://schemas.taskcluster.net/pulse/v1/irc-request.json#/properties/payload/properties/channel
         */
        public String channel;

        /**
         * User to post the message to. Please note that you **must** supply
         * either `user` or `channel`, you cannot supply both.
         *
         * Syntax:     ^[A-Za-z\[\]\\~_\^{|}][A-Za-z0-9\-\[\]\\~_\^{|}]{0,254}$
         * Min length: 1
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/pulse/v1/irc-request.json#/properties/payload/properties/user
         */
        public String user;
    }

    /**
     * Details for the contact method (eg. irc channel)
     *
     * See http://schemas.taskcluster.net/pulse/v1/irc-request.json#/properties/payload
     */
    public Payload payload;
}
