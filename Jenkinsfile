pipeline {
    agent any

    stages {
        
            stage('Compile') {
                steps {
                    mvn clean compile -e
                }
            }
            stage('Test') {
                steps {
                    mvn clean test -e
                }
            }
            stage('Jar') {
                steps {
                    mvnw clean package -e
                }
            }
            stage('Run') {
                steps {
                    mvnw spring-boot:run &
                }
            }
    }
}
