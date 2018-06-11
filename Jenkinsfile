pipeline {
    agent any

    tools {
        maven 'Maven 3.5.3'
        jdk 'JDK 1.8'
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
            }
        }
        stage ('Build') {
            steps {
               echo 'running Maven'
               sh 'mvn -B -C -fae -s $JENKINS_HOME/settings.xml clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage ('Integration Test') {
            steps {
                echo 'Testing...'
                sh 'mvn -B -C -fae -s $JENKINS_HOME/settings.xml -Dunit-tests.skip=true verify'
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
                }
            }
        }
        stage ('Release') {
            steps {
                echo 'Prepare release version...'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
        stage ('Deploy FAT') {
            steps {
                echo 'Deploying to FAT...'
                echo 'Running automated tests'
            }
        }
        stage ('Deploy PROD') {
            steps {
                echo 'Deploying to PROD...'
                echo 'Running smoke tests...'
            }
        }
    }
}