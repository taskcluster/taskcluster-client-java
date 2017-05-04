// The following code is AUTO-GENERATED. Please DO NOT edit.
//
// This package was generated from the schema defined at
// http://references.taskcluster.net/index/v1/api.json
package org.mozilla.taskcluster.client.index;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.TaskClusterRequestHandler;

/**
 * The task index, typically available at `index.taskcluster.net`, is
 * responsible for indexing tasks. The service ensures that tasks can be
 * located by recency and/or arbitrary strings. Common use-cases include:
 * 
 *  * Locate tasks by git or mercurial `<revision>`, or
 *  * Locate latest task from given `<branch>`, such as a release.
 * 
 * **Index hierarchy**, tasks are indexed in a dot (`.`) separated hierarchy
 * called a namespace. For example a task could be indexed with the index path
 * `some-app.<revision>.linux-64.release-build`. In this case the following
 * namespaces is created.
 * 
 *  1. `some-app`,
 *  1. `some-app.<revision>`, and,
 *  2. `some-app.<revision>.linux-64`
 * 
 * Inside the namespace `some-app.<revision>` you can find the namespace
 * `some-app.<revision>.linux-64` inside which you can find the indexed task
 * `some-app.<revision>.linux-64.release-build`. This is an example of indexing
 * builds for a given platform and revision.
 * 
 * **Task Rank**, when a task is indexed, it is assigned a `rank` (defaults
 * to `0`). If another task is already indexed in the same namespace with
 * lower or equal `rank`, the index for that task will be overwritten. For example
 * consider index path `mozilla-central.linux-64.release-build`. In
 * this case one might choose to use a UNIX timestamp or mercurial revision
 * number as `rank`. This way the latest completed linux 64 bit release
 * build is always available at `mozilla-central.linux-64.release-build`.
 * 
 * Note that this does mean index paths are not immutable: the same path may
 * point to a different task now than it did a moment ago.
 * 
 * **Indexed Data**, when a task is retrieved from the index the result includes
 * a `taskId` and an additional user-defined JSON blob that was indexed with
 * the task.
 * 
 * **Entry Expiration**, all indexed entries must have an expiration date.
 * Typically this defaults to one year, if not specified. If you are
 * indexing tasks to make it easy to find artifacts, consider using the
 * artifact's expiration date.
 * 
 * **Valid Characters**, all keys in a namespace `<key1>.<key2>` must be
 * in the form `/[a-zA-Z0-9_!~*'()%-]+/`. Observe that this is URL-safe and
 * that if you strictly want to put another character you can URL encode it.
 * 
 * **Indexing Routes**, tasks can be indexed using the API below, but the
 * most common way to index tasks is adding a custom route to `task.routes` of the
 * form `index.<namespace>`. In order to add this route to a task you'll
 * need the scope `queue:route:index.<namespace>`. When a task has
 * this route, it will be indexed when the task is **completed successfully**.
 * The task will be indexed with `rank`, `data` and `expires` as specified
 * in `task.extra.index`. See the example below:
 * 
 * ```js
 * {
 *   payload:  { /* ... * / },
 *   routes: [
 *     // index.<namespace> prefixed routes, tasks CC'ed such a route will
 *     // be indexed under the given <namespace>
 *     "index.mozilla-central.linux-64.release-build",
 *     "index.<revision>.linux-64.release-build"
 *   ],
 *   extra: {
 *     // Optional details for indexing service
 *     index: {
 *       // Ordering, this taskId will overwrite any thing that has
 *       // rank <= 4000 (defaults to zero)
 *       rank:       4000,
 * 
 *       // Specify when the entries expire (Defaults to 1 year)
 *       expires:          new Date().toJSON(),
 * 
 *       // A little informal data to store along with taskId
 *       // (less 16 kb when encoded as JSON)
 *       data: {
 *         hgRevision:   "...",
 *         commitMessae: "...",
 *         whatever...
 *       }
 *     },
 *     // Extra properties for other services...
 *   }
 *   // Other task properties...
 * }
 * ```
 * 
 * **Remark**, when indexing tasks using custom routes, it's also possible
 * to listen for messages about these tasks. For
 * example one could bind to `route.index.some-app.*.release-build`,
 * and pick up all messages about release builds. Hence, it is a
 * good idea to document task index hierarchies, as these make up extension
 * points in their own.
 *
 * @see "[Index API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs)"
 */
public class Index extends TaskClusterRequestHandler {

    protected static final String defaultBaseURL = "https://index.taskcluster.net/v1";

    public Index(Credentials credentials) {
        super(credentials, defaultBaseURL);
    }

    public Index(Credentials credentials, String baseURL) {
        super(credentials, baseURL);
    }

    public Index(String clientId, String accessToken) {
        super(new Credentials(clientId, accessToken), defaultBaseURL);
    }

    public Index(String clientId, String accessToken, String certificate) {
        super(new Credentials(clientId, accessToken, certificate), defaultBaseURL);
    }

    public Index(String baseURL) {
        super(baseURL);
    }

    public Index() {
        super(defaultBaseURL);
    }

    /**
     * Find a task by index path, returning the highest-rank task with that path. If no
     * task exists for the given path, this API end-point will respond with a 404 status.
     *
     * @see "[Find Indexed Task API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs#findTask)"
     */
    public CallSummary<EmptyPayload, IndexedTaskResponse> findTask(String indexPath) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(indexPath), IndexedTaskResponse.class);
    }

    /**
     * List the namespaces immediately under a given namespace.
     * 
     * This endpoint
     * lists up to 1000 namespaces. If more namespaces are present, a
     * `continuationToken` will be returned, which can be given in the next
     * request. For the initial request, the payload should be an empty JSON
     * object.
     *
     * @see "[List Namespaces API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs#listNamespaces)"
     */
    public CallSummary<ListNamespacesRequest, ListNamespacesResponse> listNamespaces(String namespace, ListNamespacesRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/namespaces/" + uriEncode(namespace), ListNamespacesResponse.class);
    }

    /**
     * List the tasks immediately under a given namespace.
     * 
     * This endpoint
     * lists up to 1000 tasks. If more tasks are present, a
     * `continuationToken` will be returned, which can be given in the next
     * request. For the initial request, the payload should be an empty JSON
     * object.
     * 
     * **Remark**, this end-point is designed for humans browsing for tasks, not
     * services, as that makes little sense.
     *
     * @see "[List Tasks API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs#listTasks)"
     */
    public CallSummary<ListTasksRequest, ListTasksResponse> listTasks(String namespace, ListTasksRequest payload) throws APICallFailure {
        return apiCall(payload, "POST", "/tasks/" + uriEncode(namespace), ListTasksResponse.class);
    }

    /**
     * Insert a task into the index.  If the new rank is less than the existing rank
     * at the given index path, the task is not indexed but the response is still 200 OK.
     * 
     * Please see the introduction above for information
     * about indexing successfully completed tasks automatically using custom routes.
     *
     * Required scopes:
     *
     *   * `index:insert-task:<namespace>`
     *
     * @see "[Insert Task into Index API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs#insertTask)"
     */
    public CallSummary<InsertTaskRequest, IndexedTaskResponse> insertTask(String namespace, InsertTaskRequest payload) throws APICallFailure {
        return apiCall(payload, "PUT", "/task/" + uriEncode(namespace), IndexedTaskResponse.class);
    }

    /**
     * Find a task by index path and redirect to the artifact on the most recent
     * run with the given `name`.
     * 
     * Note that multiple calls to this endpoint may return artifacts from differen tasks
     * if a new task is inserted into the index between calls. Avoid using this method as
     * a stable link to multiple, connected files if the index path does not contain a
     * unique identifier.  For example, the following two links may return unrelated files:
     * * https://index.taskcluster.net/task/some-app.win64.latest.installer/artifacts/public/installer.exe`
     * * https://index.taskcluster.net/task/some-app.win64.latest.installer/artifacts/public/debug-symbols.zip`
     * 
     * This problem be remedied by including the revision in the index path or by bundling both
     * installer and debug symbols into a single artifact.
     * 
     * If no task exists for the given index path, this API end-point responds with 404.
     *
     * Required scopes:
     *
     *   * `queue:get-artifact:<name>`
     *
     * @see "[Get Artifact From Indexed Task API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs#findArtifactFromTask)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> findArtifactFromTask(String indexPath, String name) throws APICallFailure {
        return apiCall(null, "GET", "/task/" + uriEncode(indexPath) + "/artifacts/" + uriEncode(name), EmptyPayload.class);
    }

    /**
     * Respond without doing anything.
     * This endpoint is used to check that the service is up.
     *
     * @see "[Ping Server API Documentation](https://docs.taskcluster.net/reference/core/index/api-docs#ping)"
     */
    public CallSummary<EmptyPayload, EmptyPayload> ping() throws APICallFailure {
        return apiCall(null, "GET", "/ping", EmptyPayload.class);
    }
}