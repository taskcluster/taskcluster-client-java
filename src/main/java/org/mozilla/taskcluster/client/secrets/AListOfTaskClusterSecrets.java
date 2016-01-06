package org.mozilla.taskcluster.client.secrets;

/**
* Message containing a list of secret names
*
* See http://schemas.taskcluster.net/secrets/v1/secret-list.json#
*/
public class AListOfTaskClusterSecrets {

    /**
     * Secret names
     */
    public String[] secrets;
}