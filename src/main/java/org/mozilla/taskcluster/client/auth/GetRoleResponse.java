package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Get all details about a role
 *
 * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#
 */
public class GetRoleResponse {

    /**
     * Date and time when this role was created
     *
     * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#/properties/created
     */
    public Date created;

    /**
     * Description of what this role is used for in markdown.
     * Should include who is the owner, point of contact.
     *
     * Max length: 10240
     *
     * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#/properties/description
     */
    public String description;

    /**
     * List of scopes granted anyone who assumes this role, including anything
     * granted by roles that can be assumed when you have this role.
     * Hence, this includes any scopes in-directly granted as well.
     *
     * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#/properties/expandedScopes
     */
    public String[] expandedScopes;

    /**
     * Date and time of last modification
     *
     * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#/properties/lastModified
     */
    public Date lastModified;

    /**
     * roleId of the role requested
     *
     * Syntax:     ^[\x20-\x7e]+$
     *
     * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#/properties/roleId
     */
    public String roleId;

    /**
     * List of scopes the role grants access to.  Scopes must be composed of
     * printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/auth/v1/get-role-response.json#/properties/scopes
     */
    public String[] scopes;
}
