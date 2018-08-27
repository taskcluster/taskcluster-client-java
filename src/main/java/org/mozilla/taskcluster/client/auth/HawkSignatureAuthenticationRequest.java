package org.mozilla.taskcluster.client.auth;

/**
 * Request to authenticate a hawk request.
 *
 * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#
 */
public class HawkSignatureAuthenticationRequest {

    /**
     * Authorization header, **must** only be specified if request being
     * authenticated has a `Authorization` header.
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#/properties/authorization
     */
    public String authorization;

    /**
     * Host for which the request came in, this is typically the `Host` header
     * excluding the port if any.
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#/properties/host
     */
    public String host;

    /**
     * HTTP method of the request being authenticated.
     *
     * Possible values:
     *     * "get"
     *     * "post"
     *     * "put"
     *     * "head"
     *     * "delete"
     *     * "options"
     *     * "trace"
     *     * "copy"
     *     * "lock"
     *     * "mkcol"
     *     * "move"
     *     * "purge"
     *     * "propfind"
     *     * "proppatch"
     *     * "unlock"
     *     * "report"
     *     * "mkactivity"
     *     * "checkout"
     *     * "merge"
     *     * "m-search"
     *     * "notify"
     *     * "subscribe"
     *     * "unsubscribe"
     *     * "patch"
     *     * "search"
     *     * "connect"
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#/properties/method
     */
    public String method;

    /**
     * Port on which the request came in, this is typically `80` or `443`.
     * If you are running behind a reverse proxy look for the `x-forwarded-port`
     * header.
     *
     * Mininum:    0
     * Maximum:    65535
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#/properties/port
     */
    public int port;

    /**
     * Resource the request operates on including querystring. This is the
     * string that follows the HTTP method.
     * **Note,** order of querystring elements is important.
     *
     * See https://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#/properties/resource
     */
    public String resource;
}
