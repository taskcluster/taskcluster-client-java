package org.mozilla.taskcluster.client.github;

/**
 * Any Taskcluster-specific Github repository information.
 *
 * See https://schemas.taskcluster.net/github/v1/repository.json#
 */
public class RepositoryResponse {

    /**
     * True if integration is installed, False otherwise.
     *
     * See https://schemas.taskcluster.net/github/v1/repository.json#/properties/installed
     */
    public boolean installed;
}
