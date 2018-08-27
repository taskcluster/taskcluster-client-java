package org.mozilla.taskcluster.client.notify;

/**
 * Request to send an email
 *
 * See https://schemas.taskcluster.net/notify/v1/email-request.json#
 */
public class SendEmailRequest {

    /**
     * E-mail address to which the message should be sent
     *
     * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/address
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
     * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/content
     */
    public String content;

    public class Link {

        /**
         * Where the link should point to.
         *
         * Min length: 1
         * Max length: 1024
         *
         * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/link/properties/href
         */
        public String href;

        /**
         * Text to display on link.
         *
         * Min length: 1
         * Max length: 40
         *
         * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/link/properties/text
         */
        public String text;
    }

    /**
     * Optional link that can be added as a button to the email.
     *
     * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/link
     */
    public Link link;

    /**
     * Reply-to e-mail (this property is optional)
     *
     * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/replyTo
     */
    public String replyTo;

    /**
     * Subject line of the e-mail, this is plain-text
     *
     * Min length: 1
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/subject
     */
    public String subject;

    /**
     * E-mail html template used to format your content.
     *
     * Possible values:
     *     * "simple"
     *     * "fullscreen"
     * Default:    "simple"
     *
     * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/template
     */
    public String template;
}
