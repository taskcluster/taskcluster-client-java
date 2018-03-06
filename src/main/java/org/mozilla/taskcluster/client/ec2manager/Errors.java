package org.mozilla.taskcluster.client.ec2manager;

import java.util.Date;

/**
 * This method returns a list of errors.  It currently gives the error code only
 * because we're not sure of the security implications of exposing the full
 * message.  We do store complete error messages, but are figuring out how to
 * best expose them
 *
 * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#
 */
public class Errors {

    public class ErrorsEntry {

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/az
         */
        public String az;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/code
         */
        public String code;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/instanceType
         */
        public String instanceType;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/message
         */
        public String message;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/region
         */
        public String region;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/time
         */
        public Date time;

        /**
         *
         * Possible values:
         *     * "instance-request"
         *     * "termination"
         *
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/type
         */
        public String type;

        /**
         * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors/items/properties/workerType
         */
        public String workerType;
    }

    /**
     * See http://schemas.taskcluster.net/ec2-manager/v1/errors.json#/properties/errors
     */
    public ErrorsEntry[] errors;
}
