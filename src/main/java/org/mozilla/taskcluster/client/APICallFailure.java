package org.mozilla.taskcluster.client;

@SuppressWarnings("serial")
public class APICallFailure extends Exception {

    public APICallFailure(Throwable cause) {
        super(cause);
    }
}
