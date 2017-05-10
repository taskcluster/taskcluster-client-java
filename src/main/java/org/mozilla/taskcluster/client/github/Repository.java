package org.mozilla.taskcluster.client.github;

/**
 * Any Taskcluster-specific Github repository information.
 *
 * See http://schemas.taskcluster.net/github/v1/repository.json#
 */
public class Repository {

    /**
     * True if integration is installed, False otherwise.
     *
     * See http://schemas.taskcluster.net/github/v1/repository.json#/properties/installed
     */
    public boolean installed;
}
