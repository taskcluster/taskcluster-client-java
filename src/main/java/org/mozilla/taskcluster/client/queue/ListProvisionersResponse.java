package org.mozilla.taskcluster.client.queue;

/**
 * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#
 */
public class ListProvisionersResponse {

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of provisioners.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called with `continuationToken` until you get a
     * result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/continuationToken
     */
    public String continuationToken;

    public class ProvisionerInformation {

        /**
         *
         * Syntax:     ^([a-zA-Z0-9-_]*)$
         * Min length: 1
         * Max length: 22
         *
         * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners/items/properties/provisionerId
         */
        public String provisionerId;
    }

    /**
     * See http://schemas.taskcluster.net/queue/v1/list-provisioners-response.json#/properties/provisioners
     */
    public ProvisionerInformation[] provisioners;
}
