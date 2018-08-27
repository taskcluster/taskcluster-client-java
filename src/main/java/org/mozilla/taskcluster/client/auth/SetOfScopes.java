package org.mozilla.taskcluster.client.auth;

/**
 * A set of scopes
 *
 * See https://schemas.taskcluster.net/auth/v1/scopeset.json#
 */
public class SetOfScopes {

    /**
     * List of scopes.  Scopes must be composed of printable ASCII characters and spaces.
     *
     * See https://schemas.taskcluster.net/auth/v1/scopeset.json#/properties/scopes
     */
    public String[] scopes;
}
