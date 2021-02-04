pipeline {
    agent any

    stages {
        
            stage('Compile') {
                steps {
                    "./mvnw" clean compile -e
                }
            }
            stage('Test') {
                steps {
                    "./mvnw" clean test -e
                }
            }
            stage('Jar') {
                steps {
                    "./mvnw" clean package -e
                }
            }
            stage('Run') {
                steps {
                    "./mvnw" spring-boot:run &
                }
            }
    }
}
