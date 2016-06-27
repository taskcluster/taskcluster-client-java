package org.mozilla.taskcluster.client.hooks;

/**
 * Information about no firing of the hook (e.g., a new hook)
 *
 * See http://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[2]
 */
public class NoFire {

    /**
     *
     * Possible values:
     *     * "no-fire"
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[2]/properties/result
     */
    public String result;
}
