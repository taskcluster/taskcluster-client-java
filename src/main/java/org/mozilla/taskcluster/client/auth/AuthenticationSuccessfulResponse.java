package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]
 */
public class AuthenticationSuccessfulResponse {

    /**
     * The `clientId` that made this request.  This may be the `id` supplied in
     * the Authorization header, or in the case of a named temporary credential
     * may be embedded in the payload.  In any case, this clientId can be used
     * for logging, auditing, and identifying the credential but **must** not be
     * used for access control.  That's what scopes are for.
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]/properties/clientId
     */
    public String clientId;

    /**
     * The expiration time for the credentials used to make this request.
     * This should be treated as the latest time at which the authorization
     * is valid.  For most cases, where the access being authorized occurs
     * immediately, this field can be ignored, as the value will always be
     * in the future if the status is `auth-success`.
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]/properties/expires
     */
    public Date expires;

    /**
     * Payload as extracted from `Authentication` header. This property is
     * only present if a hash is available. You are not required to validate
     * this hash, but if you do, please check `scheme` to ensure that it's
     * on a scheme you support.
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]/properties/hash
     */
    public Object hash;

    /**
     * Authentication scheme the client used. Generally, you don't need to
     * read this property unless `hash` is provided and you want to validate
     * the payload hash. Additional values may be added in the future.
     *
     * Possible values:
     *     * "hawk"
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]/properties/scheme
     */
    public String scheme;

    /**
     * List of scopes the client is authorized to access.  Scopes must be
     * composed of printable ASCII characters and spaces.
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]/properties/scopes
     */
    public String[] scopes;

    /**
     * The kind of response, `auth-failed` or `auth-success`.
     *
     * Possible values:
     *     * "auth-success"
     *
     * See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-response.json#/anyOf[0]/properties/status
     */
    public String status;
}
