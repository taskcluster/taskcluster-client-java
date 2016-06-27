package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Token for submitting statistics to statsum.
 *
 * See http://schemas.taskcluster.net/auth/v1/statsum-token-response.json#
 */
public class StatsumTokenResponse {

    /**
     * Base URL for the statsum server this project is allocated on.
     *
     * See http://schemas.taskcluster.net/auth/v1/statsum-token-response.json#/properties/baseUrl
     */
    public String baseUrl;

    /**
     * Time at which the token expires and should not be used anymore.
     *
     * See http://schemas.taskcluster.net/auth/v1/statsum-token-response.json#/properties/expires
     */
    public Date expires;

    /**
     * Project name that the token grants access to.
     *
     * See http://schemas.taskcluster.net/auth/v1/statsum-token-response.json#/properties/project
     */
    public String project;

    /**
     * JWT token to be used as `Bearer <token>` when submitting data to statsum.
     *
     * See http://schemas.taskcluster.net/auth/v1/statsum-token-response.json#/properties/token
     */
    public String token;
}
