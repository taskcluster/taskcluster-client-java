package org.mozilla.taskcluster.client.hooks;

/**
 * Secret token for a trigger
 *
 * See https://schemas.taskcluster.net/hooks/v1/trigger-token-response.json#
 */
public class TriggerTokenResponse {

    /**
     * See https://schemas.taskcluster.net/hooks/v1/trigger-token-response.json#/properties/token
     */
    public String token;
}
