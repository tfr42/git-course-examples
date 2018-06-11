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

Admin user login created during initial setup.

#### How to retrieve the initial password for Jenkins from the container

    $ docker exec -it git-course-examples_jenkins_1 bash -c "cat /var/jenkins_home/secrets/initialAdminPassword"
    
#### JAVA_HOME of installed OpenJDK 8

`JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/`    

### Nexus OSS
http://localhost:8081/nexus

Default admin user login (admin/admin123)

### gitlab CE
http://localhost:8083/

Default admin user login (root). Password set during first login. 

### Sonarqube
http://localhost:9090/

## How to stop the services

    $ docker-compose stop

