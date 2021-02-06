pipeline {
  agent any

  stages {

    stage('Compile') {
      steps {

        sh './mvnw clean compile -e'

      }
    }
    stage('Test DxC') {
      steps {

        sh './mvnw test -Dtest=DevopsApicationTests -DfailIfNoTests=false -e'

      }
    }

    stage('Skip Test') {
      steps {

        sh './mvnw install -DskipTests'

      }
    }

    stage('Jar') {
      steps {
        sh './mvnw clean package -e'

      }
    }

    stage('Run') {
      steps {
        sh "start ./mvnw spring-boot:run &"
				sleep 20

      }
    }
    stage('Test Postman') {
        steps {
            sh "newman run postman\\LabDevops-v2.postman_collection.json -e postman\\DevOpsLabUnidad4.postman_environment.json"
        }
    }

    stage('Enable Selenium') {
      steps {

        sh './mvnw install -DskipTests=false'

      }
    }

    stage('Test Selenium') {
        steps {
            sh "./mvnw test -Dtest=SeleniumTests -DfailIfNoTests=false -e"
        }
    }

   
    stage('Test de Carga Jmeter') {
        steps {
            sh "./mvnw verify -Pperformance"
        }
    }
 
  }
}