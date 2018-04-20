package org.mozilla.taskcluster.client.queue;

/**
 * Object holding two signed URLs for an azure queue, one for fetching
 * messages, and another for deleting messages. Remember to `claimTask`
 * before deleting the message, and delete message even if the `claimTask`
 * operation fails with a 400 status code. Don't delete it on other status
 * codes!
 *
 * See http://schemas.taskcluster.net/queue/v1/poll-task-urls-response.json#/properties/queues/items
 */
public class SignedURLsForAQueue {

    /**
     * Signed URL to delete messages that have been received using the
     * `signedPollUrl`. You **must** do this to avoid receiving the same
     * message again.
     * To use this URL you must substitute `{{messageId}}` and
     * `{{popReceipt}}` with `MessageId` and `PopReceipt` from the XML
     * response the `signedPollUrl` gave you. It is important that you
     * `encodeURIComponent` both `MessageId` and `PopReceipt` prior to
     * substitution, otherwise you will experience intermittent failures!
     * Note this URL only works with `DELETE` request.
     *
     * Syntax:     ^https://
     *
     * See http://schemas.taskcluster.net/queue/v1/poll-task-urls-response.json#/properties/queues/items/properties/signedDeleteUrl
     */
    public String signedDeleteUrl;

    /**
     * Signed URL to get message from the Azure Queue Storage queue,
     * that holds messages for the given `provisionerId` and `workerType`.
     * Note that this URL returns XML, see documentation for the Azure
     * Queue Storage
     * [REST API](http://msdn.microsoft.com/en-us/library/azure/dd179474.aspx)
     * for details.
     * When you have a message you can use `claimTask` to claim the task.
     * You will need to parse the XML response and base64 decode and
     * JSON parse the `MessageText`.
     * After you have called `claimTask` you **must** us the
     * `signedDeleteUrl` to delete the message.
     * **Remark**, you are allowed to append `&numofmessages=N`,
     * where N < 32, to the URLs if you wish to obtain more than one
     * message at the time.
     *
     * See http://schemas.taskcluster.net/queue/v1/poll-task-urls-response.json#/properties/queues/items/properties/signedPollUrl
     */
    public String signedPollUrl;
}
