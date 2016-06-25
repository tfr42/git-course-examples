# Build Infrastructure based on the following Docker images

## Jenkins CI
Using the official image from https://hub.docker.com/_/jenkins/:

    $ docker pull jenkins

## Nexus Repository
Using the official image from https://hub.docker.com/r/sonatype/nexus/:

    $ docker pull sonatype/nexus

## gitlab
Using the offical image from https://hub.docker.com/r/gitlab/gitlab-ce/:

    $ docker pull gitlab/gitlab-ce

## How to start the infrastructure

    $ docker-compose up jenkins

## How to access the services

### Jenkins
http://192.168.99.100:8082/

### Nexus OSS
http://192.168.99.100:8081/

### gitlab
http://192.168.99.100:8083/

## How to stop the services

    $ docker-compose stop jenkins
    