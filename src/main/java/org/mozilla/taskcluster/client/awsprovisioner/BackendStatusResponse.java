package org.mozilla.taskcluster.client.awsprovisioner;

import java.util.Date;

/**
* Backend Status Response
*
* See http://schemas.taskcluster.net/aws-provisioner/v1/backend-status-response.json#
*/
public class BackendStatusResponse {

    /**
     * A date when the provisioner backend process last completed an iteration.
     * This does not imply success, rather it is to make sure that the process
     * is alive
     */
    public Date lastCheckedIn;

    /**
     * A string from Deadman's Snitch which describes the status.  See
     * https://deadmanssnitch.com/docs/api/v1#listing-your-snitches for an
     * explanation of this value
     */
    public String status;
}