package org.mozilla.taskcluster.client.index;

import java.util.Date;

/**
 * Representation of an indexed task.
 *
 * See http://schemas.taskcluster.net/index/v1/indexed-task-response.json#
 */
public class IndexedTaskResponse {

	/**
	 * Data that was reported with the task. This is an arbitrary JSON object.
	 */
	public Object data;

	/**
	 * Date at which this entry expires from the task index.
	 */
	public Date expires;

	/**
	 * Namespace of the indexed task, used to find the indexed task in the
	 * index.
	 */
	public String namespace;

	/**
	 * If multiple tasks are indexed with the same `namespace` the task with the
	 * highest `rank` will be stored and returned in later requests. If two
	 * tasks has the same `rank` the latest task will be stored.
	 */
	public int rank;

	/**
	 * Unique task identifier, this is UUID encoded as [URL-safe
	 * base64](http://tools.ietf.org/html/rfc4648#section-5) and stripped of `=`
	 * padding.
	 */
	public String taskId;
}
