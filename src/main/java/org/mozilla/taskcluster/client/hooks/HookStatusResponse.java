package org.mozilla.taskcluster.client.hooks;

import java.util.Date;

/**
 * A snapshot of the current status of a hook.
 *
 * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#
 */
public class HookStatusResponse {

    /**
     * Information about the last time this hook fired.  This property is only present
     * if the hook has fired at least once.
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/lastFire
     */
    public Object lastFire;

    /**
     * The next time this hook's task is scheduled to be created. This property
     * is only present if there is a scheduled next time. Some hooks don't have
     * any schedules.
     *
     * See https://schemas.taskcluster.net/hooks/v1/hook-status.json#/properties/nextScheduledDate
     */
    public Date nextScheduledDate;
}
