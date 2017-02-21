package org.mozilla.taskcluster.client.github;

/**
 * Check if Repository has Integration
 *
 * See http://schemas.taskcluster.net/github/v1/is-installed-for.json#
 */
public class IsInstalledFor {

    /**
     * True if integration is installed, False otherwise.
     *
     * See http://schemas.taskcluster.net/github/v1/is-installed-for.json#/properties/installed
     */
    public boolean installed;
}
