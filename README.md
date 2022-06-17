# scheduler Project

Microservice built using Quarkus, the Supersonic Subatomic Java Framework, hibernate, panache and postgresql database

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Install required operators
Run the command below to install operators 

```
helm upgrade -i install-operators ./deploy/k8s/helm/install-operators -f ./deploy/k8s/helm/install-operators/Values.yaml
```

## Create an instance of Postgresql cluster
Run command below to create the Postgresql cluster

```
helm upgrade -i create-postgresqlcluster ./deploy/k8s/helm/create-postgresqlcluster -f ./deploy/k8s/helm/create-postgresqlcluster/Values.yaml
```

## Building the application
This project is configured to use quarkus openshift extension

When we created the postgresql cluster in step before, a project named schedule is already created on the openshift cluster, be sure to set the project to that by running that command below

```
oc project schedule
```

Package the application into a container image. We are using the builtin container registry in OpenShift, you can always change this in the properties file if you want to push the container image to an external registry, be sure to login to that registry using podman before if you decide to do that.

```
./mvnw clean package -Dquarkus.container-image.build=true
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/scheduler-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
