package org.mozilla.taskcluster.client.ec2manager;

/**
 * See http://schemas.taskcluster.net/ec2-manager/v1/create-key-pair.json#
 */
public class SshPublicKey {

    /**
     * An OpenSSH format Public Key as described by tools.ietf.org/html/rfc4253#section-6.6
     *
     * Syntax:     ^(ssh-\S*)\s*(\S*)\s*(.*)$
     *
     * See http://schemas.taskcluster.net/ec2-manager/v1/create-key-pair.json#/properties/pubkey
     */
    public String pubkey;
}
