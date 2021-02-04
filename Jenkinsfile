pipeline {
  agent any

  stages {

    stage('Compile') {
      steps {

        bat 'mvn clean compile -e'

      }
    }
    stage('Test') {
      steps {

        bat 'mvn clean test -e'

      }
    }
    stage('Jar') {
      steps {
        bat 'mvn clean package -e'

      }
    }

        stage('Test Postman') {
            steps {
                bat "newman run Test-WebServices.postman_collection.json"
            }
        }
    

        stage('Test Load') {
            steps {
                bat "mvn verify -Pperformance"
            }
        }
 
  }
}