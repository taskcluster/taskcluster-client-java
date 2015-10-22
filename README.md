# taskcluster-client-java
<img hspace="20" align="left" src="https://tools.taskcluster.net/lib/assets/taskcluster-120.png" />
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

* [Index](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/index/Index.html)  
  The task index is responsible for indexing tasks.

* [Purge Cache](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/purgecache/PurgeCache.html)  
  The purge-cache service is responsible for publishing a pulse message for workers, so they can purge cache upon request.

* [Queue](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/queue/Queue.html)  
  The queue is responsible for accepting tasks and track their state as they are executed by workers.

* [Scheduler](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/scheduler/Scheduler.html)  
  The task-graph scheduler is responsible for accepting task-graphs and scheduling tasks for evaluation by the queue as their dependencies are satisfied.

* [Secrets](http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/secrets/Secrets.html)  
  The secrets service is responsible for managing secure data in TaskCluster.

### AMQP APIs

Currently AMQP APIs are not supported in the java client.

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
      <version>1.0.1</version>
    </dependency>
  </dependencies>
</project>
```

The taskcluster-client artifacts are now available from the [maven central repository](http://central.sonatype.org/):

* [Search Results](http://search.maven.org/#search|gav|1|g%3A%22org.mozilla.taskcluster%22%20AND%20a%3A%22taskcluster-client%22)
* [Directory Listing](https://repo1.maven.org/maven2/org/mozilla/taskcluster/taskcluster-client/1.0.1/)

## Calling API End-Points

To invoke an API end-point, instantiate one of the HTTP API classes (from section [HTTP APIs](#http-apis)).
In the following example we instantiate an instance of the `Queue` client class and use it to create a task.

```java
import org.mozilla.taskcluster.client.*;
import org.mozilla.taskcluster.client.queue.*;

...

    // Instantiate the Queue Client class
    Queue queue = new Queue(System.getenv("TASKCLUSTER_CLIENT_ID"), System.getenv("TASKCLUSTER_ACCESS_TOKEN"));

    // Certificate must also be provided if using temporary credentials
    // Queue queue = new Queue(System.getenv("TASKCLUSTER_CLIENT_ID"), System.getenv("TASKCLUSTER_ACCESS_TOKEN"), System.getenv("TASKCLUSTER_CERTIFICATE"));

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

See the [HTTP API javadocs](#http-apis) for more information, or browse the [unit tests](https://github.com/taskcluster/taskcluster-client-java/tree/master/src/test/java/org/mozilla/taskcluster) for further examples.
## Building

[![GoDoc](https://godoc.org/github.com/taskcluster/taskcluster-client-java?status.svg)](https://godoc.org/github.com/taskcluster/taskcluster-client-java)

The java libraries provided by this client are auto-generated in
[go](https://golang.org/) using the schemas defined in
http://references.taskcluster.net/manifest.json combined with supplementary
information stored in
[apis.json](https://github.com/taskcluster/taskcluster-client-java/blob/master/codegenerator/model/apis.json).

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

Contributions are welcome. Please fork, and issue a Pull Request back with an explanation of your changes.
