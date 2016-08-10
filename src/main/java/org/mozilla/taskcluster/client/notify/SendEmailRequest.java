package org.mozilla.taskcluster.client.notify;

/**
 * Request to send an email
 *
 * See http://schemas.taskcluster.net/notify/v1/email-request.json#
 */
public class SendEmailRequest {

    /**
     * E-mail address to which the message should be sent
     *
     * See http://schemas.taskcluster.net/notify/v1/email-request.json#/properties/address
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
     * See http://schemas.taskcluster.net/notify/v1/email-request.json#/properties/content
     */
    public String content;

    /**
     * Reply-to e-mail (this property is optional)
     *
     * See http://schemas.taskcluster.net/notify/v1/email-request.json#/properties/replyTo
     */
    public String replyTo;

    /**
     * Subject line of the e-mail, this is plain-text
     *
     * Min length: 1
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/notify/v1/email-request.json#/properties/subject
     */
    public String subject;
}
