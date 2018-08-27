package org.mozilla.taskcluster.client.hooks;

/**
 * See https://schemas.taskcluster.net/hooks/v1/hook-metadata.json#
 */
public class HookMetadata {

    /**
     * Long-form of the hook's purpose and behavior
     *
     * Max length: 32768
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-metadata.json#/properties/description
     */
    public String description;

    /**
     * Whether to email the owner on an error creating the task.
     *
     * Default:    true
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-metadata.json#/properties/emailOnError
     */
    public boolean emailOnError;

    /**
     * Human readable name of the hook
     *
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-metadata.json#/properties/name
     */
    public String name;

    /**
     * Email of the person or group responsible for this hook.
     *
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-metadata.json#/properties/owner
     */
    public String owner;
}
