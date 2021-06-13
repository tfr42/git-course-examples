FROM jenkins/jenkins:lts

MAINTAINER "Torsten Friebe" <tfr42@sourceforge.com>

USER root
# install tools
RUN apt-get update && apt-get install -y ansible maven ant gradle curl gnupg
# install docker cli
ENV DOCKERVERSION=20.10.6
RUN curl -fsSLO https://download.docker.com/linux/static/stable/x86_64/docker-${DOCKERVERSION}.tgz \
  && tar xzvf docker-${DOCKERVERSION}.tgz --strip 1 \
                 -C /usr/local/bin docker/docker \
  && rm docker-${DOCKERVERSION}.tgz
# drop back to the regular jenkins user

USER jenkins
# Install jenkins plugins
RUN /usr/local/bin/install-plugins.sh git github github-branch-source gitlab-api gitlab-hook gitlab-plugin bitbucket \
    docker-workflow docker-slaves workflow-aggregator workflow-scm-step \
    pipeline-maven pipeline-utility-steps pipeline-github \
    blueocean ansible gradle ant junit cobertura mailer ssh maven-plugin trac trac-publisher-plugin dependency-check-jenkins-plugin \
    aws-credentials aws-beanstalk-publisher-plugin \
    timestamper