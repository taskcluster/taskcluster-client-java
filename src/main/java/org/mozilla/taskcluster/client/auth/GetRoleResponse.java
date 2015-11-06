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
     */
    public Date created;

    /**
     * Description of what this role is used for in markdown.
     * Should include who is the owner, point of contact.
     */
    public String description;

    /**
     * List of scopes granted anyone who assumes this role, including anything
     * granted by roles that can be assumed when you have this role.
     * Hence, this includes any scopes in-directly granted as well.
     */
    public String[] expandedScopes;

    /**
     * Date and time of last modification
     */
    public Date lastModified;

    /**
     * roleId of the role requested
     */
    public String roleId;

    /**
     * List of scopes the role grants access to.  Scopes must be composed of
     * printable ASCII characters and spaces.
     */
    public String[] scopes;
}