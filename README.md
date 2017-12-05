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

    $ docker-compose up -d

## How to access the services

Depending on the Operating System and Docker integration the IP may differ.

### Jenkins CI
http://localhost:8082/

### Nexus OSS
http://localhost:8081/nexus

### gitlab CE
http://localhost:8083/

### Sonarqube
http://localhost:9090/

## How to stop the services

    $ docker-compose stop
