#!/usr/bin/env groovy

def TEST = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true

pipeline {
    agent any
    triggers {
            cron('H */5 * * *')
    }
    tools {
        maven 'maven-3.6'
        jdk 'jdk-8'
    }
    options {
        timestamps()
    }
    environment {
        POM_ARTIFACT = sh script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true
//        POM_VERSION = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
        POM_VERSION = readMavenPom().getVersion()
        AWS_REGION = 'eu-west-1'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
                sh 'mvn -version'
                sh 'java -version'
                sh 'git --version'
                sh 'docker --version'
                echo '$env.TEST'
                echo '$TEST'
            }
        }
        stage ('Build') {
            steps {
               echo 'running Maven for project ${env.POM_ARTIFACT}:${env.POM_VERSION}'
               sh 'mvn -B -C -fae -s $JENKINS_HOME/settings.xml clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*Test.xml'
                }
            }
        }
        stage ('Integration Tests') {
            steps {
                echo 'Testing...'
                sh 'mvn -B -C -fae -s $JENKINS_HOME/settings.xml -Dskip.unit.tests=true verify'
            }
            post {
                always {
                    junit 'target/failsafe-reports/**/*IT.xml'
                }
            }
        }
        stage ('Quality Checks') {
            steps {
                echo 'Checking...'
                sh 'mvn -B -C -fae -s $JENKINS_HOME/settings.xml site'
            }
            post {
                success {
                    cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
                    dependencyCheckPublisher pattern: '**/target/site/dependency-check-report.xml'
                }
            }
        }
        stage ('Release') {
            when {
                // check if branch is master
                branch 'master'
            }
            agent { label 'docker' }
            steps {
                echo 'Prepare release version...'
                sh 'mvn -B clean release:prepare release:perform'
                echo 'Build docker image...'
                sh 'docker build -t seminar/helloworld:latest --build-arg jarfile=./target/helloWorld-${pom_version}.jar .'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
        stage ('Deploy FAT') {
            when {
                // check if branch is master
                branch 'master'
            }
            agent { label 'docker' }
            steps {
                echo 'Deploying to FAT...'
                sh 'docker run -p 48080:8080 --rm seminar/helloworld'
                echo 'Running automated tests'
            }
        }
        stage ('Deploy PROD') {
            when {
                // check if branch is master
                branch 'master'
            }
            agent { label 'aws' }
            steps {
                echo 'Deploying to PROD...'
                echo "${env.AWS_REGION}"
                echo 'Running smoke tests...'
            }
            post {
              always {
                cleanWs()
              }
            }
        }
    }
}