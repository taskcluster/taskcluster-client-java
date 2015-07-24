package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * List of clients and all their details as JSON for import/export.
 *
 * See http://schemas.taskcluster.net/auth/v1/exported-clients.json#
 */
public class ExportedClients {

	/**
	 * AccessToken used for authenticating requests
	 */
	public String accessToken;

	/**
	 * ClientId of the client scopes is requested about
	 */
	public String clientId;

	/**
	 * Description of what these credentials are used for in markdown. Should
	 * include who is the owner, point of contact. Why it is scoped as is, think
	 * of this as documentation.
	 */
	public String description;

	/**
	 * Date and time where the clients credentials are set to expire
	 */
	public Date expires;

	/**
	 * Human readable name of this set of credentials, typical
	 * component/server-name or IRC nickname of the user.
	 */
	public String name;

	/**
	 * List of scopes the client is authorized to access
	 */
	public String[] scopes;
}
