package org.mozilla.taskcluster.client;

public class ExtAuthField {

    public Certificate certificate;
    public String[]    authorizedScopes;

    public ExtAuthField(Certificate certificate, String[] authorizedScopes) {
        this.certificate = certificate;
        this.authorizedScopes = authorizedScopes;
    }
}
