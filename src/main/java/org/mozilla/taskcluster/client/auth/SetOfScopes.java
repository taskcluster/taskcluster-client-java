package org.mozilla.taskcluster.client.auth;

/**
* A set of scopes
*
* See http://schemas.taskcluster.net/auth/v1/scopeset.json#
*/
public class SetOfScopes {

    /**
     * List of scopes.  Scopes must be composed of printable ASCII characters and spaces.
     */
    public String[] scopes;
}