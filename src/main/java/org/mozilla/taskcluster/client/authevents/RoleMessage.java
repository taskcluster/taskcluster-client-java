package org.mozilla.taskcluster.client.authevents;

/**
 * Message reporting that a role has changed
 *
 * See http://schemas.taskcluster.net/auth/v1/role-message.json#
 */
public class RoleMessage {

    /**
     * `roleId` of the role that was changed
     *
     * Syntax:     ^[\x20-\x7e]+$
     *
     * See http://schemas.taskcluster.net/auth/v1/role-message.json#/properties/roleId
     */
    public String roleId;

    /**
     * Message version number
     *
     * Possible values:
     *     * 1
     *
     * See http://schemas.taskcluster.net/auth/v1/role-message.json#/properties/version
     */
    public int version;
}
