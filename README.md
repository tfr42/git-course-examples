# Build Infrastructure based on the following Docker images

## Jenkins CI
Using the official image from https://hub.docker.com/r/jenkins/jenkins:

    $ docker pull jenkins/jenkins

## Nexus 3 Repository Manager
Using the official image from https://hub.docker.com/r/sonatype/nexus3/:

    $ docker pull sonatype/nexus3

## gitlab
Using the official image from https://hub.docker.com/r/gitlab/gitlab-ce/:

    $ docker pull gitlab/gitlab-ce

## How to start the infrastructure

    $ docker-compose up -d

## How to access the services

Depending on the Operating System and Docker integration the IP may differ.

### Jenkins CI
http://localhost:8082/

Admin user login created during initial setup.

#### How to retrieve the initial password for Jenkins from the container

    $ docker-compose exec jenkins bash -c "cat /var/jenkins_home/secrets/initialAdminPassword"
    
#### JAVA_HOME of installed OpenJDK 8

    $ `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/` 

#### Install OpenJDK 8 

Add a new AdoptOpenJDK 8 with:
Installation type "Unzip archive" and 
Download URL: https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u242-b08/OpenJDK8U-jdk_x64_linux_hotspot_8u242b08.tar.gz
Subdirectory: jdk8u242-b08

JAVA_HOME=/var/jenkins_home/tools/hudson.model.JDK/jdk-8/jdk8u242-b08

#### Configure tools

Maven
M2_HOME=/usr/share/maven
Ansible 

### Nexus OSS
http://localhost:8081/nexus

Default admin user login (admin/admin123)

    $ docker-compose exec nexus bash -c "cat /nexus-data/admin.password"

### gitlab CE
http://localhost:8083/

Default admin user login (root). Password set during first login.

#### How to set the external URL of gitlab

    $ `docker exec -it git-course-examples_gitlab_1 vi /etc/gitlab/gitlab.rb`

find and replace:
external_url "http://<HOST>:<PORT>/" 

### Sonarqube
http://localhost:9090/

## How to stop the services

    $ docker-compose stop

## Troubleshooting

### Cleanup

`docker run --rm -v /var/run/docker.sock:/var/run/docker.sock -v /etc:/etc spotify/docker-gc`
or
`docker system prune -a`

HowTo
docker exec -it git-course-examples_gitlab_1 update-permissions