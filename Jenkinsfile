pipeline {
    agent any

    stages {
        stage ('Build') {
            steps {
                echo 'Building...'
                sh './helloWorld.sh'
            }
        }
        stage ('Integration Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage ('Quality Checks') {
            steps {
                echo 'Checking...'
            }
        }
        stage ('Release') {
            steps {
                echo 'Prepare release version...'
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