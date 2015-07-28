# taskcluster-client-java

<img src="https://tools.taskcluster.net/lib/assets/taskcluster-120.png" />

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
      <version>1.0.0</version>
    </dependency>
  </dependencies>
</project>
```

The taskcluster-client artifacts will shortly be available from the [maven central repository](http://search.maven.org/#search|gav|1|g%3A%22org.mozilla.taskcluster%22%20AND%20a%3A%22taskcluster-client%22). Until then, it is recommended to clone this repository and run `mvn clean install` to build them, in order to have them available for your project(s).

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
        CallSummary<TaskDefinition, TaskStatusResponse> cs = queue.defineTask(taskId, td);

        // Process API response
        String state = cs.ResponsePayload.status.state;
        System.out.println("State is " + state);

    } catch (APICallFailure e) {
        // handle exception ...
    }

...
```

See the [HTTP API javadocs](#http-apis) for more information, or browse the [unit tests](https://github.com/taskcluster/taskcluster-client-java/tree/master/src/test/java/org/mozilla/taskcluster) for further examples.
## Building

The libraries provided by this client are auto-generated based on the schemas listed under
http://references.taskcluster.net/manifest.json combined with the supplementary information stored in
[apis.json](https://github.com/taskcluster/taskcluster-client-java/blob/master/codegenerator/model/apis.json).

In order to completely regenerate all of the HTTP and AMQP libraries, please run [build.sh](https://github.com/taskcluster/taskcluster-client-java/blob/master/build.sh)
found in the top level directory. This will completely regenerate the library. Please note you will need an active internet connection as the build process must
download several json files and schemas in order to build the library. The code generation requires go (golang) is installed on your system, and java, and apache maven. All three need to be setup and configured correctly.

The code which generates the library can all be found under the top level [codegenerator](https://github.com/taskcluster/taskcluster-client-java/tree/master/codegenerator)
directory.

## Contributing

Contributions are welcome. Please fork, and issue a Pull Request back with an explanation of your changes.
