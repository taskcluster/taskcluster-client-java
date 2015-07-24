package org.mozilla.taskcluster.client.awsprovisioner;

/**
 * Secrets from the provisioner
 *
 * See
 * http://schemas.taskcluster.net/aws-provisioner/v1/get-secret-response.json#
 */
public class GetSecretResponse {

	/**
	 * Generated Temporary credentials from the Provisioner
	 */
	public class Credentials {
		public String accessToken;
		public String certificate;
		public String clientId;
	}

	public Credentials credentials;

	/**
	 * Free-form object which contains secrets from the worker type definition
	 */
	public Object data;
}
