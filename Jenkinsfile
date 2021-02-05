pipeline {
  agent any

  stages {

    stage('Compile') {
      steps {

        ./mvnw clean compile -e

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

    stage('Run') {
      steps {
        bat "start mvn spring-boot:run &"
				sleep 20

      }
    }
        stage('Test Postman') {
            steps {
                bat "newman run postman\\LabDevops-v2.postman_collection.json -e postman\\DevOpsLabUnidad4.postman_environment.json"
            }
        }
    

        stage('Test de Carga Jmeter') {
            steps {
                bat "mvn verify -Pperformance"
            }
        }
 
  }
}