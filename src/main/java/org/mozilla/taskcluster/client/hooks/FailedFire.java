package org.mozilla.taskcluster.client.hooks;

import java.util.Date;

/**
 * Information about an unsuccessful firing of the hook
 *
 * See http://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[1]
 */
public class FailedFire {

    /**
     * The error that occurred when firing the task.  This is typically,
     * but not always, an API error message.
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[1]/properties/error
     */
    public Object error;

    /**
     *
     * Possible values:
     *     * "error"
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[1]/properties/result
     */
    public String result;

    /**
     * The time the task was created.  This will not necessarily match `task.created`.
     *
     * See http://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire/oneOf[1]/properties/time
     */
    public Date time;
}
