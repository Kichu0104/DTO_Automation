pipeline {
    agent any

    tools {
        jdk 'Java'
        maven 'Maven'
    }

    stages {
        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo 'Build SUCCESS'
        }
        failure {
            echo 'Build FAILED'
        }
    }
}
