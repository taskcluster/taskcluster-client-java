package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* List of artifacts for a given `taskId` and `runId`.
*
* See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#
*/
public class ListArtifactsResponse {

    /**
     * List of artifacts for given `taskId` and `runId`.
     */
    public class Artifacts {

        /**
         * Mimetype for the artifact that was created.
         */
        public String contentType;

        /**
         * Date and time after which the artifact created will be automatically
         * deleted by the queue.
         */
        public Date expires;

        /**
         * Name of the artifact that was created, this is useful if you want to
         * attempt to fetch the artifact.
         */
        public String name;

        /**
         * This is the `storageType` for the request that was used to create
         * the artifact.
         */
        public String storageType;
    }

    public Artifacts[] artifacts;

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of artifacts.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called with `continuationToken` until you get a
     * result without a `continuationToken`.
     */
    public String continuationToken;
}