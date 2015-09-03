package org.mozilla.taskcluster.client.auth;

/**
* Request to authenticate a hawk request.
*
* See http://schemas.taskcluster.net/auth/v1/authenticate-hawk-request.json#
*/
public class HawkSignatureAuthenticationRequest {

    /**
     * Authorization header, **must** only be specified if request being
     * authenticated has a `Authorization` header.
     */
    public String authorization;

    /**
     * Host for which the request came in, this is typically the `Host` header
     * excluding the port if any.
     */
    public String host;

    /**
     * HTTP method of the request being authenticated.
     */
    public String method;

    /**
     * Port on which the request came in, this is typically `80` or `443`.
     * If you are running behind a reverse proxy look for the `x-forwarded-port`
     * header.
     */
    public int port;

    /**
     * Resource the request operates on including querystring. This is the
     * string that follows the HTTP method.
     * **Note,** order of querystring elements is important.
     */
    public String resource;
}