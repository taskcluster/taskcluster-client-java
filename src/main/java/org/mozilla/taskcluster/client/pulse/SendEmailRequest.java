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

    public class Payload {

        /**
         * E-mail address to which the message should be sent
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/address
         */
        public String address;

        /**
         * Content of the e-mail as **markdown**, will be rendered to HTML before
         * the email is sent. Notice that markdown allows for a few HTML tags, but
         * won't allow inclusion of script tags and other unpleasantries.
         *
         * Min length: 1
         * Max length: 102400
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/content
         */
        public String content;

        public class Link {

            /**
             * Where the link should point to.
             *
             * Min length: 1
             * Max length: 1024
             *
             * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/link/properties/href
             */
            public String href;

            /**
             * Text to display on link.
             *
             * Min length: 1
             * Max length: 40
             *
             * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/link/properties/text
             */
            public String text;
        }

        /**
         * Optional link that can be added as a button to the email.
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/link
         */
        public Link link;

        /**
         * Reply-to e-mail (this property is optional)
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/replyTo
         */
        public String replyTo;

        /**
         * Subject line of the e-mail, this is plain-text
         *
         * Min length: 1
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload/properties/subject
         */
        public String subject;
    }

    /**
     * Details for the contact method (eg. email address)
     *
     * See http://schemas.taskcluster.net/pulse/v1/email-request.json#/properties/payload
     */
    public Payload payload;
}
