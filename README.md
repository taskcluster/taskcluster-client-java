# taskcluster-client-java
<img hspace="20" align="left" src="https://tools.taskcluster.net/lib/assets/taskcluster-120.png" />
[Java docs](http://taskcluster.github.io/taskcluster-client-java/apidocs)

A java port of taskcluster-client.

Complete javadoc documentation [here](http://taskcluster.github.io/taskcluster-client-java/apidocs).

This library provides the following 6 packages to interface with TaskCluster:

### HTTP APIs
* http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/auth/Auth.html
* http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/awsprovisioner/AwsProvisioner.html
* http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/index/Index.html
* http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/purgecache/PurgeCache.html
* http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/queue/Queue.html
* http://taskcluster.github.io/taskcluster-client-java/apidocs/org/mozilla/taskcluster/client/scheduler/Scheduler.html

### AMQP APIs

Currently AMQP APIs are not supported in the java client.

## Example programs

To get you started quickly, see the test:

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

## Bugs
Rebuilding isn't entirely working yet (code formatting problems, one minor bug still to fix). Therefore if you rebuild, you might need to hand tweak a couple of files afterwards.
