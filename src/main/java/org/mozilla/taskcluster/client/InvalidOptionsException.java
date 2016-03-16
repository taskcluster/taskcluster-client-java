package org.mozilla.taskcluster.client;

@SuppressWarnings("serial")
public class InvalidOptionsException extends Exception {
    public InvalidOptionsException(Throwable cause) {
        super(cause);
    }
}
