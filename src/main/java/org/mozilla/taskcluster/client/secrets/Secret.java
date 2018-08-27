package org.mozilla.taskcluster.client.secrets;

import java.util.Date;

/**
 * Message containing a TaskCluster Secret
 *
 * See https://schemas.taskcluster.net/secrets/v1/secret.json#
 */
public class Secret {

    /**
     * An expiration date for this secret.
     *
     * See https://schemas.taskcluster.net/secrets/v1/secret.json#/properties/expires
     */
    public Date expires;

    /**
     * The secret value to be encrypted.
     *
     * See https://schemas.taskcluster.net/secrets/v1/secret.json#/properties/secret
     */
    public Object secret;
}
