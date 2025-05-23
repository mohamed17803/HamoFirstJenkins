pipeline {
    agent any
    
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/mohamed17803/HamoFirstJenkins.git'
            }
        }

        stage('Continuous Testing') {
            steps {
                script {
                    def startTime = System.currentTimeMillis()
                    def endTime = startTime + (11 * 60 * 60 * 1000) // 11 hours in milliseconds

                    while (System.currentTimeMillis() < endTime) {
                        try {
                            // Clean workspace
                            bat 'mvn clean'

                            // Run tests with TestNG
                            bat 'mvn test -Dtest=TestNG.xml'

                            // Generate Allure report
                            allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/allure-results']]
                            ])

                            // Wait 1 minute between runs
                            sleep(time: 1, unit: 'MINUTES')
                        } catch (Exception e) {
                            echo "Error during execution: ${e.toString()}"
                            // Continue running despite errors
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            // Cleanup or notifications can be added here
            echo "11-hour test cycle completed or stopped"
        }
    }
}