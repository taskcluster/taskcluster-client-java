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

The taskcluster-client artifacts should be available from the [maven central repository](http://central.sonatype.org/) from August 2015 onwards. Until then, it is recommended to clone this repository and run `mvn clean install` in order to have it available for your project(s).

## Example programs

Have a look at the following test to see how you can interact with Taskcluster using the Taskcluster Client.

* https://github.com/taskcluster/taskcluster-client-java/blob/master/src/test/java/org/mozilla/taskcluster/APITest.java#L21

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
