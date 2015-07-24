package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Scopes and expiration date for a client
 *
 * See http://schemas.taskcluster.net/auth/v1/client-scopes-response.json#
 */
public class GetClientScopesResponse {

	/**
	 * ClientId of the client scopes is requested about
	 */
	public String clientId;

	/**
	 * Date and time where the clients credentials are set to expire
	 */
	public Date expires;

	/**
	 * List of scopes the client is authorized to access
	 */
	public String[] scopes;
}
