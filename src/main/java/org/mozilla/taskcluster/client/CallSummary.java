package org.mozilla.taskcluster.client;

public class CallSummary<Request, Response> {

    public Request  requestPayload;
    public Response responsePayload;
    public String   requestBody;
    public String   responseBody;
}
