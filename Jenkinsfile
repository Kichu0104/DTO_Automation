pipeline {
    agent any

    triggers {
        cron('0 22 * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Kichu1014/DTO_Automation.git'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {

        always {

            junit '**/surefire-reports/*.xml'

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'emailable-report.html',
                reportName: 'Automation Report'
            ])
        }

        success {
            echo 'Execution Successful'
        }

        failure {
            echo 'Execution Failed'
        }
    }
}
