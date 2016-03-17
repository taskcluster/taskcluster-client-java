package org.mozilla.taskcluster;

import org.mozilla.taskcluster.client.Credentials;

public class TempCredsTestCase {
    public String      description;
    public Credentials permCreds;
    public String      seed;
    public String      start;
    public String      expiry;
    public String      tempCredsName;
    public String[]    tempCredsScopes;
    public Credentials expectedTempCreds;
}