package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* Request for a run of a task to be resolved with an exception
*
* See http://schemas.taskcluster.net/queue/v1/task-exception-request.json#
*/
public class TaskExceptionRequest {

    /**
     * Reason that the task is resolved with an exception. This is a subset
     * of the values for `resolvedReason` given in the task status structure.
     * Please, report `worker-shutdown` if the run failed because the worker
     * had to shutdown (spot node disappearing).
     * And report `malformed-payload` if the `task.payload` doesn't match the
     * schema for the worker payload, or referenced dependencies doesn't exists.
     * In either case, you should still log the error to a log file under the
     * specific run.
     * In case if `worker-shutdown` the queue will immediately **retry** the
     * task, by making a new run. This is much faster than ignoreing the issue
     * and letting the task _retry_ by claim expiration. For any other _reason_
     * reported the queue will not retry the task.
     */
    public Object reason;
}