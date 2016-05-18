package org.mozilla.taskcluster.client.authevents;

/**
* Message reporting that a role has changed
*
* See http://schemas.taskcluster.net/auth/v1/role-message.json#
*/
public class RoleMessage {

    /**
     * `roleId` of the role that was changed
     */
    public String roleId;

    /**
     * Message version number
     */
    public Object version;
}