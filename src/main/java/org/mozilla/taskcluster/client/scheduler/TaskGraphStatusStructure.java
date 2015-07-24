package org.mozilla.taskcluster.client.scheduler;

/**
 * A representation of **task-graph status** as known by the scheduler, without
 * the state of all individual tasks.
 *
 * See http://schemas.taskcluster.net/scheduler/v1/task-graph-status.json#
 */
public class TaskGraphStatusStructure {

	/**
	 * Unique identifier for task-graph scheduler managing the given task-graph
	 */
	public String schedulerId;

	/**
	 * Task-graph state, this enum is **frozen** new values will **not** be
	 * added.
	 */
	public Object state;

	/**
	 * Unique task-graph identifier, this is UUID encoded as [URL-safe
	 * base64](http://tools.ietf.org/html/rfc4648#section-5) and stripped of `=`
	 * padding.
	 */
	public String taskGraphId;
}
