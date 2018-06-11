pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh './helloWorld.sh'
            }
        }
        stage('Integration Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Quality Checks') {
            steps {
                echo 'Checking...'
            }
        }
        stage('Deploy FAT') {
            steps {
                echo 'Deploying to FAT...'
            }
            steps {
                echo 'Running automated tests'
            }
        }
        stage('Deploy PROD') {
            steps {
                echo 'Deploying to PROD...'
            }
            steps {
                echo 'Running smoke tests...'
            }
        }
    }
}