# taskcluster-client-java

[![logo](https://tools.taskcluster.net/b2d854df0391f8b777f39a486ebbc868.png)](https://tools.taskcluster.net/b2d854df0391f8b777f39a486ebbc868.png)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.mozilla.taskcluster/taskcluster-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mozilla.taskcluster/taskcluster-client/)
[![Build Status](https://travis-ci.org/taskcluster/taskcluster-client-java.svg?branch=master)](http://travis-ci.org/taskcluster/taskcluster-client-java)
[![JavaDoc](https://img.shields.io/badge/javadoc-reference-blue.svg)](http://taskcluster.github.io/taskcluster-client-java/apidocs)
[![Coverage Status](https://coveralls.io/repos/taskcluster/taskcluster-client-java/badge.svg?branch=master&service=github)](https://coveralls.io/github/taskcluster/taskcluster-client-java?branch=master)
[![License](https://img.shields.io/badge/license-MPL%202.0-orange.svg)](http://mozilla.org/MPL/2.0)

A java port of taskcluster-client.

## HTTP APIs

* [Auth](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/auth/Auth.html)  
  Authentication related API end-points for taskcluster.

* [AWS Provisioner](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/awsprovisioner/AwsProvisioner.html)  
  The AWS Provisioner is responsible for provisioning instances on EC2 for use in TaskCluster.

* [EC2 Manager](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/ec2manager/EC2Manager.html)  
  A taskcluster service which manages EC2 instances.

* [Github](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/github/Github.html)  
  The Github service is responsible for publishing pulse messages for supported Github events.

* [Hooks](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/hooks/Hooks.html)  
  Hooks are a mechanism for creating tasks in response to events.

* [Index](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/index/Index.html)  
  The task index is responsible for indexing tasks.

* [Login](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/login/Login.html)  
  The Login service serves as the interface between external authentication systems and TaskCluster credentials.

* [Notify](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/notify/Notify.html)  
  The notification service listens for tasks with associated notifications and sends them.

* [Purge Cache](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/purgecache/PurgeCache.html)  
  The purge-cache service is responsible for publishing a pulse message for workers, so they can purge cache upon request.

* [Queue](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/queue/Queue.html)  
  The queue is responsible for accepting tasks and track their state as they are executed by workers.

* [Secrets](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/secrets/Secrets.html)  
  The secrets service is responsible for managing secure data in TaskCluster.

### AMQP APIs

Currently AMQP APIs are not supported in the java client.

### Client utilities

* [Credentials](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/Credentials.html)  
  For credentials operations, such as:
    * Creating temporary credentials from permanent credentials
    * Limiting credentials to a set of authorized scopes

## Using

### Maven

In order to use this library from your maven project, simply include it as a project dependency:

```
<project>
  ...
  <dependencies>
    ...
    <dependency>
      <groupId>org.mozilla.taskcluster</groupId>
      <artifactId>taskcluster-client</artifactId>
    </dependency>
  </dependencies>
</project>
```

The taskcluster-client artifacts are now available from the [maven central repository](http://central.sonatype.org/):

* [Search Results](http://search.maven.org/#search|gav|1|g%3A%22org.mozilla.taskcluster%22%20AND%20a%3A%22taskcluster-client%22)
* [Directory Listing](https://repo1.maven.org/maven2/org/mozilla/taskcluster/taskcluster-client/)

## Calling API End-Points

To invoke an API end-point, instantiate one of the HTTP API classes (from
section [HTTP APIs](#http-apis)).  In the following example we instantiate an
instance of the `Queue` client class and use it to create a task.

```java
import org.mozilla.taskcluster.client.*;
import org.mozilla.taskcluster.client.queue.*;

...

	// Create credentials, e.g. from environment variables...
    Credentials creds = new Credentials(
        System.getenv("TASKCLUSTER_CLIENT_ID"),
        System.getenv("TASKCLUSTER_ACCESS_TOKEN"),
        System.getenv("TASKCLUSTER_CERTIFICATE")
    );

    // Instantiate the Queue client class
    Queue queue = new Queue(creds);

    // Supply a unique task name
    String taskId = "...";

    // Define the task
    TaskDefinition td = new TaskDefinition();

    // Set properties, as required...
    td.created = new java.util.Date();
    td.provisionerId = "...";
    td.routes = new String[] { "...", "...", "..." };
    td.XYZ = ...

    // Execute the API call
    try {
        TaskStatusResponse tsr = queue.defineTask(taskId, td).responsePayload;

        // Process API response
        System.out.println("State is " + tsr.status.state);

    } catch (APICallFailure e) {
        // handle exception ...
    }

...
```
## Temporary credentials

You can generate temporary credentials from permanent credentials using the
java client. This may be useful if you wish to issue credentials to a third
party. See [temporary-credentials](/docs/manual/apis/temporary-credentials) for
more information. Both named and unnamed temporary credentials are supported,
although named credentials are preferred if you are not sure which type to use.

### Example

```java
package org.mozilla.taskcluster;

import java.util.Date;

import org.mozilla.taskcluster.client.APICallFailure;
import org.mozilla.taskcluster.client.CallSummary;
import org.mozilla.taskcluster.client.Credentials;
import org.mozilla.taskcluster.client.EmptyPayload;
import org.mozilla.taskcluster.client.InvalidOptionsException;
import org.mozilla.taskcluster.client.queue.ListArtifactsResponse;
import org.mozilla.taskcluster.client.queue.ListArtifactsResponse.Artifacts;
import org.mozilla.taskcluster.client.queue.Queue;

/**
 * This simple demo lists the artifacts in run 0 of task U7On2dUVS9KlEgw7LUaCMQ.
 * It creates permanent credentials from environment variables
 * TASKCLUSTER_CLIENT_ID and TASKCLUSTER_ACCESS_TOKEN, and then creates
 * temporary credentials, valid for 24 hours, from these permanent credentials.
 * It queries the Queue using the temporary credentials, and with limited
 * authorized scopes.
 *
 * Note, the queue.listArtifacts(...) call doesn't require any scopes, the
 * generation of temporary credentials, and limiting via authorized scopes is
 * purely illustrative. The TASKCLUSTER_CLIENT_ID must satisfy
 * auth:create-client:demo-client/taskcluster-client-java, though.
 */
public class Demo {
    public static void main(String[] args) {
        Credentials permCreds = new Credentials(
            System.getenv("TASKCLUSTER_CLIENT_ID"),
            System.getenv("TASKCLUSTER_ACCESS_TOKEN")
        );
        Date now = new Date();
        try {
            Credentials tempCreds = permCreds.createTemporaryCredentials(
                "demo-client/taskcluster-client-java",
                new String[] {
                    "assume:legacy-permacred"
                },
                // valid immediately
                now,
                // expire in 24 hours
                new Date(now.getTime() + 1000 * 60 * 60 * 24)
            );
            tempCreds.authorizedScopes = new String[] { "queue:get-artifact:private/build/*" };
            Queue queue = new Queue(tempCreds);
            CallSummary<EmptyPayload, ListArtifactsResponse> cs = queue.listArtifacts("U7On2dUVS9KlEgw7LUaCMQ", "0");
            ListArtifactsResponse artifacts = cs.responsePayload;
            System.out.println("Artifacts:");
            for (Artifacts artifact : artifacts.artifacts) {
                System.out.println("  * " + artifact.name);
            }
            System.out.println("Done.");
        } catch (InvalidOptionsException e) {
            System.err.println("Could not create temporary credentials");
            e.printStackTrace();
        } catch (APICallFailure e) {
            System.err.println("Could not query the Queue service");
            e.printStackTrace();
        }
    }
}
```

See the [HTTP API javadocs](#http-apis) for more information, or browse the [unit
tests](https://github.com/taskcluster/taskcluster-client-java/tree/master/src/test/java/org/mozilla/taskcluster)
for further examples.

## Building

The java libraries provided by this client are auto-generated in
[go](https://golang.org/) (not java!) using the schemas defined in
http://references.taskcluster.net/manifest.json combined with supplementary
information stored in
[apis.json](https://github.com/taskcluster/taskcluster-client-java/blob/master/codegenerator/model/apis.json).

[![GoDoc](https://godoc.org/github.com/taskcluster/taskcluster-client-java?status.svg)](https://godoc.org/github.com/taskcluster/taskcluster-client-java)

In order to completely regenerate all of the HTTP and AMQP libraries, please run
[build.sh](https://github.com/taskcluster/taskcluster-client-java/blob/master/build.sh)
found in the top level directory. This will completely regenerate the library.
Please note you will need an active internet connection as the build process
must download several json files and schemas in order to build the library. The
code generation requires go (golang) is installed on your system, and java, and
apache maven. All three need to be setup and configured correctly.

The code which generates the library can all be found under the top level
[codegenerator](https://github.com/taskcluster/taskcluster-client-java/tree/master/codegenerator)
directory.

## Contributing

Contributions are welcome. Please fork, and issue a Pull Request back with an
explanation of your changes.
