package org.mozilla.taskcluster.client.auth;

import java.util.Date;

/**
 * Response to a request for an Shared-Access-Signature to access an Azure
 * Blob Storage container.
 *
 * See http://schemas.taskcluster.net/auth/v1/azure-container-response.json#
 */
public class AzureBlobSharedAccessSignature {

    /**
     * Date and time of when the Shared-Access-Signature expires.
     *
     * See http://schemas.taskcluster.net/auth/v1/azure-container-response.json#/properties/expiry
     */
    public Date expiry;

    /**
     * Shared-Access-Signature string. This is the querystring parameters to
     * be appened after `?` or `&` depending on whether or not a querystring is
     * already present in the URL.
     *
     * See http://schemas.taskcluster.net/auth/v1/azure-container-response.json#/properties/sas
     */
    public String sas;
}
