package org.mozilla.taskcluster.client.schedulerevents;

/**
 * Messages as posted to `scheduler/v1/task-graph-extended` informing the world
 * that a task-graph have been extended.
 *
 * See
 * http://schemas.taskcluster.net/scheduler/v1/task-graph-extended-message.json#
 */
public class TaskGraphExtendedMessage {
	public TaskGraphStatusStructure status;

	/**
	 * Message version
	 */
	public Object version;
}
