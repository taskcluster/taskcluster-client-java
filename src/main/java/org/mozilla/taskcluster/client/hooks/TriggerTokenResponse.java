package org.mozilla.taskcluster.client.hooks;

/**
 * Secret token for a trigger
 *
 * See http://schemas.taskcluster.net/hooks/v1/trigger-token-response.json#
 */
public class TriggerTokenResponse {

    /**
     * See http://schemas.taskcluster.net/hooks/v1/trigger-token-response.json#/properties/token
     */
    public String token;
}
