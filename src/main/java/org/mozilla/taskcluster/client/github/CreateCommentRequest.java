package org.mozilla.taskcluster.client.github;

/**
 * Write a new comment on a GitHub Issue or Pull Request.
 * Full specification on [GitHub docs](https://developer.github.com/v3/issues/comments/#create-a-comment)
 *
 * See http://schemas.taskcluster.net/github/v1/create-comment.json#
 */
public class CreateCommentRequest {

    /**
     * The contents of the comment.
     *
     * See http://schemas.taskcluster.net/github/v1/create-comment.json#/properties/body
     */
    public String body;
}
