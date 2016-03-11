package org.mozilla.taskcluster.client;

public class InvalidOptionsException extends Exception {
  public InvalidOptionsException (Throwable cause){
    super(cause);
  }
}
