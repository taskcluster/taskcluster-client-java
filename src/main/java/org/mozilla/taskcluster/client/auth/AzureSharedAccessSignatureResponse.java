package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Response to a request for an Shared-Access-Signature to access and Azure
 * Table Storage table.
 *
 * See http://schemas.taskcluster.net/auth/v1/azure-table-access-response.json#
 */
public class AzureSharedAccessSignatureResponse {

	/**
	 * Date and time of when the Shared-Access-Signature expires.
	 */
	public Date expiry;

	/**
	 * Shared-Access-Signature string. This is the querystring parameters to be
	 * appened after `?` or `&` depending on whether or not a querystring is
	 * already present in the URL.
	 */
	public String sas;
}
