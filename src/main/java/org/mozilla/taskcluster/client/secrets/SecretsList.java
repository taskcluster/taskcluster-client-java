package org.mozilla.taskcluster.client.secrets;

/**
 * Message containing a list of secret names
 *
 * See https://schemas.taskcluster.net/secrets/v1/secret-list.json#
 */
public class SecretsList {

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of provisioners.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called with `continuationToken` until you get a
     * result without a `continuationToken`.
     *
     * See https://schemas.taskcluster.net/secrets/v1/secret-list.json#/properties/continuationToken
     */
    public String continuationToken;

    /**
     * Secret names
     *
     * See https://schemas.taskcluster.net/secrets/v1/secret-list.json#/properties/secrets
     */
    public String[] secrets;
}
