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
                    mvn clean package -e
                }
            }
            stage('Run') {
                steps {
                    mvn spring-boot:run &
                }
            }
    }
}
