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

    stage('Run') {
      steps {
        bat 'nohup start mvn spring-boot:run &'
				sleep 20

      }
    }

    

        stage('Test Postman') {
            steps {
                bat "newman run postman\\LabDevops-v2.postman_collection.json -e postman\\DevOpsLabUnidad4.postman_environment.json"
            }
        }
    

        stage('Test Load') {
            steps {
                bat "mvn verify -Pperformance"
            }
        }
 
  }
}