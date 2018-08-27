package org.mozilla.taskcluster.client.auth;

/**
 * Access credentials and urls for the Sentry project.
 * Credentials will expire in 24-48 hours, you should refresh them within
 * 24 hours.
 *
 * See https://schemas.taskcluster.net/auth/v1/sentry-dsn-response.json#/properties/dsn
 */
public class Dsn {

    /**
     * Access credential and URL for public error reports.
     * These credentials can be used for up-to 24 hours.
     * This is for use in client-side applications only.
     *
     * See https://schemas.taskcluster.net/auth/v1/sentry-dsn-response.json#/properties/dsn/properties/public
     */
    public String public1;

    /**
     * Access credential and URL for private error reports.
     * These credentials can be used for up-to 24 hours.
     * This is for use in serser-side applications and should **not** be
     * leaked.
     *
     * See https://schemas.taskcluster.net/auth/v1/sentry-dsn-response.json#/properties/dsn/properties/secret
     */
    public String secret;
}
