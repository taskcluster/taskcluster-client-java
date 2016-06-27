package org.mozilla.taskcluster.client.auth;

/**
 * Data to create or update a role.
 *
 * See http://schemas.taskcluster.net/auth/v1/create-role-request.json#
 */
public class CreateRoleRequest {

    /**
     * Description of what this role is used for in markdown.
     * Should include who is the owner, point of contact.
     *
     * Max length: 10240
     *
     * See http://schemas.taskcluster.net/auth/v1/create-role-request.json#/properties/description
     */
    public String description;

    /**
     * List of scopes the role grants access to.  Scopes must be composed of
     * printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/auth/v1/create-role-request.json#/properties/scopes
     */
    public String[] scopes;
}
