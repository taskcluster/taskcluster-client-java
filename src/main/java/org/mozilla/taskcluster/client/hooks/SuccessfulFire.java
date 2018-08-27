package org.mozilla.taskcluster.client.hooks;

import java.util.Date;

/**
 * Information about a successful firing of the hook
 *
 * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[0]
 */
public class SuccessfulFire {

    /**
     *
     * Possible values:
     *     * "success"
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[0]/properties/result
     */
    public String result;

    /**
     * The task created
     *
     * Syntax:     ^[A-Za-z0-9_-]{8}[Q-T][A-Za-z0-9_-][CGKOSWaeimquy26-][A-Za-z0-9_-]{10}[AQgw]$
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[0]/properties/taskId
     */
    public String taskId;

    /**
     * The time the task was created.  This will not necessarily match `task.created`.
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[0]/properties/time
     */
    public Date time;
}
