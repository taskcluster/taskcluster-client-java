package org.mozilla.taskcluster.client.notify;

/**
 * Optional link that can be added as a button to the email.
 *
 * See https://schemas.taskcluster.net/notify/v1/email-request.json#/properties/link
 */
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
