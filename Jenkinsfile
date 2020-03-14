pipeline {
    agent any

    tools {
        maven 'maven-3.6'
        jdk 'jdk-8'
    }
    environment {
        pom_artifact = readMavenPom().getArtifactId()
        pom_version = readMavenPom().getVersion()
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
            }
        }
        stage ('Build') {
            steps {
               echo 'running Maven'
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
                    dependencyCheckAnalyzer datadir: '', hintsFile: '', includeCsvReports: false, includeHtmlReports: false, includeJsonReports: false, includeVulnReports: false, isAutoupdateDisabled: false, outdir: '', scanpath: 'target/*.xml', skipOnScmChange: false, skipOnUpstreamChange: false, suppressionFile: '', zipExtensions: ''
                    findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: '**/findbugsXml.xml', unHealthy: ''
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
                echo '${pom_version}'
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
                echo 'Running smoke tests...'
            }
        }
    }
}