package org.mozilla.taskcluster.client.scheduler;

/**
 * Response for a request for task-graph information
 *
 * See
 * http://schemas.taskcluster.net/scheduler/v1/task-graph-info-response.json#
 */
public class TaskGraphInfoResponse {

	/**
	 * Required task metadata
	 */
	public class Metadata {

		/**
		 * Human readable description of task-graph, **explain** what it does!
		 */
		public String description;

		/**
		 * Human readable name of task-graph
		 */
		public String name;

		/**
		 * E-mail of person who caused this task-graph, e.g. the person who did
		 * `hg push`
		 */
		public String owner;

		/**
		 * Link to source of this task-graph, should specify file, revision and
		 * repository
		 */
		public String source;
	}

	public Metadata metadata;
	public TaskGraphStatusStructure status;

	/**
	 * Arbitrary key-value tags (only strings limited to 4k)
	 */
	public Object tags;
}
