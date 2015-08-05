package org.mozilla.taskcluster.client.queue;

import java.util.Date;

/**
* Response to request for poll task urls.
*
* See http://schemas.taskcluster.net/queue/v1/poll-task-urls-response.json#
*/
public class PollTaskUrlsResponse {

    /**
     * Date and time after which the signed URLs provided in this response
     * expires and not longer works for authentication.
     */
    public Date expires;

    /**
     * List of signed URLs for queues to poll tasks from, they must be called
     * in the order they are given. As the first entry in this array **may**
     * have higher priority.
     */
    public class Queues {

        /**
         * Signed URL to delete messages that have been received using the
         * `signedPollUrl`. You **must** do this to avoid receiving the same
         * message again.
         * To use this URL you must substitute `{{messageId}}` and
         * `{{popReceipt}}` with `MessageId` and `PopReceipt` from the XML
         * response the `signedPollUrl` gave you. Note this URL only works
         * with `DELETE` request.
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
         * You will need to parse the XML reponse and base64 decode and
         * JSON parse the `MessageText`.
         * After you have called `claimTask` you **must** us the
         * `signedDeleteUrl` to delete the message.
         * **Remark**, you are allowed to append `&numofmessages=N`,
         * where N < 32, to the URLs if you wish to obtain more than one
         * message at the time.
         */
        public String signedPollUrl;
    }

    public Queues[] queues;
}