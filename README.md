# scheduler Project

Microservice built using Quarkus, the Supersonic Subatomic Java Framework, hibernate, panache and postgresql database

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Create a new project
Create a new project called schedule by running command below

```
oc new-project schedule
```
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

There is also a github actions workflow called `installOperatorsandCreateCluster` that you can kick off that automates installation of operators and creation of a Postgresql cluster

## Building the application
This project is configured to use quarkus openshift extension. We can package the application into a container image. We are using the builtin container registry in OpenShift to push the container images, you can always change this in the properties file if you want to push the container image to an external registry, be sure to login to that registry using podman before if you decide to do that. Run command below to package and create the container image

```
./mvnw clean package -Dquarkus.container-image.build=true
```

## Deploying this application into OpenShift
Run the command below to deploy the scheduler service into Openshift

```
helm upgrade -i scheduler ./deploy/k8s/helm/scheduler -f ./deploy/k8s/helm/scheduler/Values.yaml
```

There is a ci workflow that can be run which automates the above steps
