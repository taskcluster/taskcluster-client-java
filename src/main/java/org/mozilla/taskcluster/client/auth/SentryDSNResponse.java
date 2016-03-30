package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
* Sentry DSN for submitting errors.
*
* See http://schemas.taskcluster.net/auth/v1/sentry-dsn-response.json#
*/
public class SentryDSNResponse {

    /**
     * Access credentials and urls for the Sentry project.
     * Credentials will expire in 24-48 hours, you should refresh them within
     * 24 hours.
     */
    public class Dsn {

        /**
         * Access credential and URL for public error reports.
         * These credentials can be used for up-to 24 hours.
         * This is for use in client-side applications only.
         */
        public String public1;

        /**
         * Access credential and URL for private error reports.
         * These credentials can be used for up-to 24 hours.
         * This is for use in serser-side applications and should **not** be
         * leaked.
         */
        public String secret;
    }

    public Dsn dsn;

    /**
     * Expiration time for the credentials. The credentials should not be used
     * after this time. They might not be revoked immediately, but will be at
     * some arbitrary point after this date-time.
     */
    public Date expires;

    /**
     * Project name that the DSN grants access to.
     */
    public String project;
}