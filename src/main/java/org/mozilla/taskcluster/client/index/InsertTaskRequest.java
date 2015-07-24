package org.mozilla.taskcluster.client.index;

import java.util.Date;

/**
 * Representation of an a task to be indexed.
 *
 * See http://schemas.taskcluster.net/index/v1/insert-task-request.json#
 */
public class InsertTaskRequest {

	/**
	 * This is an arbitrary JSON object. Feel free to put whatever data you want
	 * here, but do limit it, you'll get errors if you store more than 32KB. So
	 * stay well, below that limit.
	 */
	public Object data;

	/**
	 * Date at which this entry expires from the task index.
	 */
	public Date expires;

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
