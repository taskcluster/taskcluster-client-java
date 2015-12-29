package org.mozilla.taskcluster.client.secrets;

import java.util.Date;

/**
* Message containing a TaskCluster Secret
*
* See http://schemas.taskcluster.net/secrets/v1/secret.json#
*/
public class Secret {

    /**
     * An expiration date for this secret.
     */
    public Date expires;

    /**
     * The secret value to be encrypted.
     */
    public Object secret;
}