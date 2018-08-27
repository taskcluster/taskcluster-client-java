package org.mozilla.taskcluster.client.auth;

/**
 * Token for connecting a worker to webhooktunnel proxy
 *
 * See https://schemas.taskcluster.net/auth/v1/webhooktunnel-token-response.json#
 */
public class WebhooktunnelTokenResponse {

    /**
     * websocket url at which proxy is hosted
     *
     * See https://schemas.taskcluster.net/auth/v1/webhooktunnel-token-response.json#/properties/proxyUrl
     */
    public String proxyUrl;

    /**
     * jwt token to be used as `Bearer <token>` when connecting to proxy.
     *
     * See https://schemas.taskcluster.net/auth/v1/webhooktunnel-token-response.json#/properties/token
     */
    public String token;

    /**
     * id for proxy connection
     *
     * See https://schemas.taskcluster.net/auth/v1/webhooktunnel-token-response.json#/properties/tunnelId
     */
    public String tunnelId;
}
