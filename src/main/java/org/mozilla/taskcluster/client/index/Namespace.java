package org.mozilla.taskcluster.client.index;

import java.util.Date;

/**
 * Representation of a namespace that contains indexed tasks.
 *
 * See https://schemas.taskcluster.net/index/v1/list-namespaces-response.json#/properties/namespaces/items
 */
public class Namespace {

    /**
     * Date at which this entry, and by implication all entries below it,
     * expires from the task index.
     *
     * See https://schemas.taskcluster.net/index/v1/list-namespaces-response.json#/properties/namespaces/items/properties/expires
     */
    public Date expires;

    /**
     * Name of namespace within it's parent namespace.
     *
     * See https://schemas.taskcluster.net/index/v1/list-namespaces-response.json#/properties/namespaces/items/properties/name
     */
    public String name;

    /**
     * Fully qualified name of the namespace, you can use this to list
     * namespaces or tasks under this namespace.
     *
     * Max length: 255
     *
     * See https://schemas.taskcluster.net/index/v1/list-namespaces-response.json#/properties/namespaces/items/properties/namespace
     */
    public String namespace;
}
