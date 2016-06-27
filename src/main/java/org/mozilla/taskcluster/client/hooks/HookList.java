package org.mozilla.taskcluster.client.hooks;

/**
 * List of hooks
 *
 * See http://schemas.taskcluster.net/hooks/v1/list-hooks-response.json#
 */
public class HookList {

    /**
     * See http://schemas.taskcluster.net/hooks/v1/list-hooks-response.json#/properties/hooks
     */
    public HookDefinition[] hooks;
}
