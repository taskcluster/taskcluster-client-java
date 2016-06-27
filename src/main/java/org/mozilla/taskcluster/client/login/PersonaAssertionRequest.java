package org.mozilla.taskcluster.client.login;

/**
 * See http://schemas.taskcluster.net/login/v1/persona-request.json#
 */
public class PersonaAssertionRequest {

    /**
     * The Persona assertion from `navigator.id.get`
     *
     * See http://schemas.taskcluster.net/login/v1/persona-request.json#/properties/assertion
     */
    public String assertion;

    /**
     * The audience against which to verify the assertion, in the format
     * `https://site.com:443`.  This must be from a whitelist of sites
     * configured in the login service.
     *
     * See http://schemas.taskcluster.net/login/v1/persona-request.json#/properties/audience
     */
    public String audience;
}
