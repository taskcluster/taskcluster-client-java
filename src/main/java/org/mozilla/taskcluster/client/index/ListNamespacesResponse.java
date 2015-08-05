package org.mozilla.taskcluster.client.index;

import java.util.Date;

/**
* Response from a request to list namespaces within a given namespace.
*
* See http://schemas.taskcluster.net/index/v1/list-namespaces-response.json#
*/
public class ListNamespacesResponse {

    /**
     * A continuation token is returned if there are more results than listed
     * here. You can optionally provide the token in the request payload to
     * load the additional results.
     */
    public String continuationToken;

    /**
     * List of namespaces.
     */
    public class Namespaces {

        /**
         * Date at which this entry, and by implication all entries below it,
         * expires from the task index.
         */
        public Date expires;

        /**
         * Name of namespace within it's parent namespace.
         */
        public Object name;

        /**
         * Fully qualified name of the namespace, you can use this to list
         * namespaces or tasks under this namespace.
         */
        public String namespace;
    }

    public Namespaces[] namespaces;
}