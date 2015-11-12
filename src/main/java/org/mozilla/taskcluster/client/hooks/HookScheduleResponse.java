package org.mozilla.taskcluster.client.hooks;

import java.util.Date;

/**
* A description of when a hook's task will be created, and the next scheduled time
*
* See http://schemas.taskcluster.net/hooks/v1/hook-schedule.json#
*/
public class HookScheduleResponse {

    /**
     * The next time this hook's task is scheduled to be created. This property
     * is only present if there is a scheduled next time. Some hooks don't have
     * any schedules.
     */
    public Date nextScheduledDate;
    public String[] schedule;
}