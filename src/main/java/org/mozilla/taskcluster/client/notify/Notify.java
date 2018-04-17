// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/notify/v1/api.json
package org.mozilla.taskcluster.client.notify;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskclusterRequestHandler;

/**
 * The notification service, typically available at `notify.taskcluster.net`
 * listens for tasks with associated notifications and handles requests to
 * send emails and post pulse messages.
 *
 * @see "[Notify API Documentation](https://docs.taskcluster.net/reference/core/notify/api-docs)"
 */
public class Notify extends TaskclusterRequestHandler {

    protected static final String defaultBaseURL = "https://notify.taskcluster.net/v1";

    public Notify(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Notify(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Notify(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Notify(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Notify(String baseURL) {
        super(baseURL);
    }

    public Notify() {
        super(defaultBaseURL);
    }

    /**
     * Send an email to `address`. The content is markdown and will be rendered
     * to HTML, but both the HTML and raw markdown text will be sent in the
     * email. If a link is included, it will be rendered to a nice button in the
     * HTML version of the email

     * Required scopes:
     *   notify:email:<address>
     *
     * @see "[Send an Email API Documentation](https://docs.taskcluster.net/reference/core/notify/api-docs#email)"
     */
    public CallSummary<SendEmailRequest, EmptyPayload> email(SendEmailRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/email", EmptyPayload.class);
    }

    /**
     * Publish a message on pulse with the given `routingKey`.

     * Required scopes:
     *   notify:pulse:<routingKey>
     *
     * @see "[Publish a Pulse Message API Documentation](https://docs.taskcluster.net/reference/core/notify/api-docs#pulse)"
     */
    public CallSummary<PostPulseMessageRequest, EmptyPayload> pulse(PostPulseMessageRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/pulse", EmptyPayload.class);
    }

    /**
     * Post a message on IRC to a specific channel or user, or a specific user
     * on a specific channel.
     * 
     * Success of this API method does not imply the message was successfully
     * posted. This API method merely inserts the IRC message into a queue
     * that will be processed by a background process.
     * This allows us to re-send the message in face of connection issues.
     * 
     * However, if the user isn't online the message will be dropped without
     * error. We maybe improve this behavior in the future. For now just keep
     * in mind that IRC is a best-effort service.

     * Required scopes:
     *   If channelRequest:
     *     notify:irc-channel:<channel>
     *
     * @see "[Post IRC Message API Documentation](https://docs.taskcluster.net/reference/core/notify/api-docs#irc)"
     */
    public CallSummary<Object, EmptyPayload> irc(Object payload) throws APICallFailure {
        return apiCall(payload, "POST", "/irc", EmptyPayload.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/notify/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}