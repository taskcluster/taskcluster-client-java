package org.mozilla.taskcluster.client.pulse;

/**
 * Request to send an email
 *
 * See http://schemas.taskcluster.net/pulse/v1/email-request.json#
 */
public class SendEmailRequest {

    /**
     * The contact method (eg. email)
     *
     * Possible values:
     *     * "email"
     *
     * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/method
     */
    public String method;

    public class TheContactPayload {

        /**
         * E-mail address to which the message should be sent
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/address
         */
        public String address;

        /**
         * Reply-to e-mail (this property is optional)
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/replyTo
         */
        public String replyTo;
    }

    /**
     * Details for the contact method (eg. email address)
     *
     * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload
     */
    public TheContactPayload payload;
}
