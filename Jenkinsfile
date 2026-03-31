pipeline {
    agent any

    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests on (chrome/edge)')
        booleanParam(name: 'CROSSBROWSER', defaultValue: false, description: 'Enable cross-browser execution')
        booleanParam(name: 'HEADLESS', defaultValue: false, description: 'Run tests in headless mode')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }

        stage('Run Tests') {
            when {
                expression { return !params.CROSSBROWSER }
            }
            steps {
                bat "mvn test -Dbrowser=${params.BROWSER}${params.HEADLESS ? 'headless' : ''}"
            }
        }

        stage('Cross Browser Tests') {
            when {
                expression { return params.CROSSBROWSER }
            }
            parallel {
                stage('Chrome') {
                    steps {
                        bat "mvn test -Dbrowser=chrome${params.HEADLESS ? 'headless' : ''}"
                    }
                }
                stage('Edge') {
                    steps {
                        bat "mvn test -Dbrowser=edge${params.HEADLESS ? 'headless' : ''}"
                    }
                }
            }
        }

        stage('Publish Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.log', allowEmptyArchive: true
            archiveArtifacts artifacts: '**/target/extent-reports/*.html', allowEmptyArchive: true
        }
    }
}
