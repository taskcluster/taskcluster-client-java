package org.mozilla.taskcluster.client.treeherderevents;

import java.util.Date;

/**
 * Definition of a single job that can be added to Treeherder
 * Project is determined by the routing key, so we don't need to specify it here.
 *
 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#
 */
public class JobDefinition {

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/buildMachine
     */
    public Machine buildMachine;

    /**
     * The name of the build system that initiated this content.  Some examples
     * are "buildbot" and "taskcluster".  But this could be any name.  This
     * value will be used in the routing key for retriggering jobs in the
     * publish-job-action task.
     *
     * Syntax:     ^[\w-]+$
     * Min length: 1
     * Max length: 25
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/buildSystem
     */
    public String buildSystem;

    /**
     * The job guids that were coalesced to this job.
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/coalesced
     */
    public String[] coalesced;

    public class Display {

        /**
         *
         * Mininum:    1
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display/properties/chunkCount
         */
        public int chunkCount;

        /**
         *
         * Mininum:    1
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display/properties/chunkId
         */
        public int chunkId;

        /**
         *
         * Syntax:     ^[\w,\+\s\[\]\(\)-]+$
         * Min length: 1
         * Max length: 100
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display/properties/groupName
         */
        public String groupName;

        /**
         *
         * Syntax:     ^[\w/?-]+$
         * Min length: 1
         * Max length: 25
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display/properties/groupSymbol
         */
        public String groupSymbol;

        /**
         *
         * Syntax:     ^[A-Za-z0-9\s_,\+\[\]\(\)-]+$
         * Min length: 1
         * Max length: 100
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display/properties/jobName
         */
        public String jobName;

        /**
         *
         * Syntax:     ^$|^[\w.-]+$
         * Min length: 0
         * Max length: 25
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display/properties/jobSymbol
         */
        public String jobSymbol;
    }

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/display
     */
    public Display display;

    /**
     * Extra information that Treeherder reads on a best-effort basis
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/extra
     */
    public Object extra;

    /**
     * True indicates this job has been retried.
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/isRetried
     */
    public boolean isRetried;

    public class JobInfo {

        public class LinksEntry {

            /**
             *
             * Min length: 1
             * Max length: 50
             *
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/label
             */
            public String label;

            /**
             *
             * Min length: 1
             * Max length: 50
             *
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/linkText
             */
            public String linkText;

            /**
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links/items/properties/url
             */
            public String url;
        }

        /**
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/links
         */
        public LinksEntry[] links;

        /**
         * Plain text description of the job and its state.  Submitted with
         * the final message about a task.
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo/properties/summary
         */
        public String summary;
    }

    /**
     * Definition of the Job Info for a job.  These are extra data
     * fields that go along with a job that will be displayed in
     * the details panel within Treeherder.
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobInfo
     */
    public JobInfo jobInfo;

    /**
     *
     * Possible values:
     *     * "build"
     *     * "test"
     *     * "other"
     * Default:    "other"
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/jobKind
     */
    public String jobKind;

    /**
     * Labels are a dimension of a platform.  The values here can vary wildly,
     * so most strings are valid for this.  The list of labels that are used
     * is maleable going forward.
     *
     * These were formerly known as "Options" within "Option Collections" but
     * calling labels now so they can be understood to be just strings that
     * denotes a characteristic of the job.
     *
     * Some examples of labels that have been used:
     *   opt    Optimize Compiler GCC optimize flags
     *   debug  Debug flags passed in
     *   pgo    Profile Guided Optimization - Like opt, but runs with profiling, then builds again using that profiling
     *   asan   Address Sanitizer
     *   tsan   Thread Sanitizer Build
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/labels
     */
    public String[] labels;

    public class LogsEntry {

        /**
         * If true, indicates that the number of errors in the log was too
         * large and not all of those lines are indicated here.
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/errorsTruncated
         */
        public boolean errorsTruncated;

        /**
         *
         * Min length: 1
         * Max length: 50
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/name
         */
        public String name;

        public class StepsEntry {

            public class ErrorsEntry {

                /**
                 *
                 * Min length: 1
                 * Max length: 255
                 *
                 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items/properties/line
                 */
                public String line;

                /**
                 *
                 * Mininum:    0
                 *
                 * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors/items/properties/linenumber
                 */
                public int linenumber;
            }

            /**
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/errors
             */
            public ErrorsEntry[] errors;

            /**
             *
             * Mininum:    0
             *
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/lineFinished
             */
            public int lineFinished;

            /**
             *
             * Mininum:    0
             *
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/lineStarted
             */
            public int lineStarted;

            /**
             *
             * Min length: 1
             * Max length: 255
             *
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/name
             */
            public String name;

            /**
             *
             * Possible values:
             *     * "success"
             *     * "fail"
             *     * "exception"
             *     * "canceled"
             *     * "unknown"
             *
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/result
             */
            public String result;

            /**
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/timeFinished
             */
            public Date timeFinished;

            /**
             * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps/items/properties/timeStarted
             */
            public Date timeStarted;
        }

        /**
         * This object defines what is seen in the Treeherder Log Viewer.
         * These values can be submitted here, or they will be generated
         * by Treeherder's internal log parsing process from the
         * submitted log.  If this value is submitted, Treeherder will
         * consider the log already parsed and skip parsing.
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/steps
         */
        public StepsEntry[] steps;

        /**
         *
         * Min length: 1
         * Max length: 255
         *
         * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs/items/properties/url
         */
        public String url;
    }

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/logs
     */
    public LogsEntry[] logs;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/origin
     */
    public Object origin;

    /**
     * Description of who submitted the job: gaia | scheduler name | username | email
     *
     * Min length: 1
     * Max length: 50
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/owner
     */
    public String owner;

    /**
     * Examples include:
     * -  'b2g'
     * -  'firefox'
     * -  'taskcluster'
     * -  'xulrunner'
     *
     * Min length: 1
     * Max length: 125
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/productName
     */
    public String productName;

    /**
     * Examples include:
     * - scheduled
     * - scheduler
     * - Self-serve: Rebuilt by foo@example.com
     * - Self-serve: Requested by foo@example.com
     * - The Nightly scheduler named 'b2g_mozilla-inbound periodic' triggered this build
     * - unknown
     *
     * Min length: 1
     * Max length: 125
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/reason
     */
    public String reason;

    /**
     * fail: A failure
     * exception: An infrastructure error/exception
     * success: Build/Test executed without error or failure
     * canceled: The job was cancelled by a user
     * unknown: When the job is not yet completed
     *
     * Possible values:
     *     * "success"
     *     * "fail"
     *     * "exception"
     *     * "canceled"
     *     * "unknown"
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/result
     */
    public String result;

    /**
     * The infrastructure retry iteration on this job.  The number of times this
     * job has been retried by the infrastructure.
     * If it's the 1st time running, then it should be 0. If this is the first
     * retry, it will be 1, etc.
     *
     * Default:    0
     * Mininum:    0
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/retryId
     */
    public int retryId;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/runMachine
     */
    public Machine runMachine;

    /**
     * unscheduled: not yet scheduled
     * pending: not yet started
     * running: currently in progress
     * completed: Job ran through to completion
     *
     * Possible values:
     *     * "unscheduled"
     *     * "pending"
     *     * "running"
     *     * "completed"
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/state
     */
    public String state;

    /**
     * This could just be what was formerly submitted as a job_guid in the
     * REST API.
     *
     * Syntax:     ^[A-Za-z0-9/+-]+$
     * Min length: 1
     * Max length: 50
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/taskId
     */
    public String taskId;

    /**
     *
     * Mininum:    1
     * Maximum:    3
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/tier
     */
    public int tier;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/timeCompleted
     */
    public Date timeCompleted;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/timeScheduled
     */
    public Date timeScheduled;

    /**
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/timeStarted
     */
    public Date timeStarted;

    /**
     * Message version
     *
     * See http://schemas.taskcluster.net/taskcluster-treeherder/v1/pulse-job.json#/properties/version
     */
    public int version;
}
