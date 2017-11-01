package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * List of artifacts for a given `taskId` and `runId`.
 *
 * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#
 */
public class ListArtifactsResponse {

    public class Artifact {

        /**
         * Mimetype for the artifact that was created.
         *
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/contentType
         */
        public String contentType;

        /**
         * Date and time after which the artifact created will be automatically
         * deleted by the queue.
         *
         * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/expires
         */
        public Date expires;

        /**
         * Name of the artifact that was created, this is useful if you want to
         * attempt to fetch the artifact.
         *
         * Max length: 1024
         *
         * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/name
         */
        public String name;

        /**
         * This is the `storageType` for the request that was used to create
         * the artifact.
         *
         * Possible values:
         *     * "blob"
         *     * "s3"
         *     * "azure"
         *     * "reference"
         *     * "error"
         *
         * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts/items/properties/storageType
         */
        public String storageType;
    }

    /**
     * List of artifacts for given `taskId` and `runId`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/artifacts
     */
    public Artifact[] artifacts;

    /**
     * Opaque `continuationToken` to be given as query-string option to get the
     * next set of artifacts.
     * This property is only present if another request is necessary to fetch all
     * results. In practice the next request with a `continuationToken` may not
     * return additional results, but it can. Thus, you can only be sure to have
     * all the results if you've called with `continuationToken` until you get a
     * result without a `continuationToken`.
     *
     * See http://schemas.taskcluster.net/queue/v1/list-artifacts-response.json#/properties/continuationToken
     */
    public String continuationToken;
}
