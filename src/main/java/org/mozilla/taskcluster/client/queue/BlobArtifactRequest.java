package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
 * Request a list of requests in a generalized format which can be run to
 * upload an artifact to storage managed by the queue.
 *
 * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]
 */
public class BlobArtifactRequest {

    /**
     * Optionally provide an encoding type which should be set as the HTTP
     * Content-Encoding header for this artifact.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/contentEncoding
     */
    public String contentEncoding;

    /**
     * The number of bytes of the entire artifact.  This must be the number
     * of bytes in the file to be uploaded.  For single part uploads, the
     * upload will fail if the number of bytes uploaded does not match this
     * value.  A single part upload (e.g. no parts list) may be at most 5GB.
     * This limit is enforced in the code because it is not possible to
     * represent all of the restrictions in a json-schema.  A multipart
     * upload may be at most 5TB, with each part other than the last being
     * between 5MB and 5GB in size.
     *
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/contentLength
     */
    public int contentLength;

    /**
     * The complete SHA256 value of the entire artifact.  This must be the
     * SHA256 of the file which is to be uploaded.  For single part uploads,
     * the upload will fail if the SHA256 value of what is uploaded does not
     * match this value
     *
     * Syntax:     ^[a-fA-F0-9]{64}$
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/contentSha256
     */
    public String contentSha256;

    /**
     * Artifact mime-type, when uploading artifact to the signed
     * `PUT` URL returned from this request this must given with the
     *  `ContentType` header. Please, provide correct mime-type,
     *  this make tooling a lot easier, specifically,
     *  always using `application/json` for JSON artifacts.
     *
     * Max length: 255
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/contentType
     */
    public String contentType;

    /**
     * Date-time after which the artifact should be deleted. Note, that
     * these will be collected over time, and artifacts may remain
     * available after expiration. S3 based artifacts are identified in
     * azure table storage and explicitly deleted on S3 after expiration.
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/expires
     */
    public Date expires;

    public class PartsEntry {

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

    /**
     * A list of parts for a multipart upload.  The presence of this list is
     * how a multipart upload is differentiated from a single part upload.
     * The items in this list represent individual parts for upload.  For a
     * multipart upload, the sha256 values provided here must match the
     * sha256 value that S3 internally computes for the upload to be
     * considered a success.  The overall sha256 value is not checked
     * explicitly because the S3 API does not allow for that, but the same
     * code that is responsible for generating the parts hashes would also
     * be generating the overall hash, which makes this less of a concern.
     * The worst case is that we have artifacts which incorrectly do not
     * validate, which is not as big of a security concern.
     *
     * Min length: 1
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/parts
     */
    public PartsEntry[] parts;

    /**
     * Artifact storage type, in this case `'blob'`
     *
     * Possible values:
     *     * "blob"
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/storageType
     */
    public String storageType;

    /**
     * The number of bytes transfered across the wire to the backing
     * datastore.  If specified, it represents the post-content-encoding
     * byte count
     *
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/transferLength
     */
    public int transferLength;

    /**
     * This is the sha256 of the bytes transfered across the wire to the
     * backing datastore.  If specified, it represents the
     * post-content-encoding sha256 value
     *
     * Syntax:     ^[a-fA-F0-9]{64}$
     *
     * See http://schemas.taskcluster.net/queue/v1/post-artifact-request.json#/oneOf[0]/properties/transferSha256
     */
    public String transferSha256;
}
