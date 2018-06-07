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
        stage('Build') {
            steps {
               echo 'running Maven'
               sh 'mvn -B -C -fae -s $JENKINS_HOME/settings.xml clean install site'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                    cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false

                }
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
    }
}