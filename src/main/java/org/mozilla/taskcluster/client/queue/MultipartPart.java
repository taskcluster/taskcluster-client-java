package org.mozilla.taskcluster.client.queue;

/**
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/parts/items
 */
public class MultipartPart {

    /**
     * The sha256 hash of the part.
     *
     * Syntax:     ^[a-fA-F0-9]{64}$
     * Min length: 64
     * Max length: 64
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/parts/items/properties/sha256
     */
    public String sha256;

    /**
     * The number of bytes in this part.  Keep in mind for S3 that
     * all but the last part must be minimum 5MB and the maximum for
     * a single part is 5GB.  The overall size may not exceed 5TB
     *
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/parts/items/properties/size
     */
    public int size;
}
