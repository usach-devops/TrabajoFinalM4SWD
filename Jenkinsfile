pipeline {
  agent any

  stages {

    stage('Compile') {
      steps {

        sh './mvnw clean compile -e'
      }
    }

/*
    stage('Test Dxc & Jar') {
      steps {
        sh './mvnw clean package -e'

      }
    }
    */

    stage('Run') {
      steps {
        sh './mvnw mvn spring-boot:run &'
			sleep 20

      }
    }

    stage('Test Postman') {
        steps {
            sh "newman run ./postman/LabDevops-v2.postman_collection.json -e ./postman/DevOpsLabUnidad4.postman_environment.json"
        }
    }

    stage('Test Selenium') {
        steps {
            sh "./mvnw test -Dtest=SeleniumTST -DfailIfNoTests=false -e"
        }
    }

   
    // stage('Test de Carga Jmeter') {
    //     steps {
    //         bat "mvn verify -Pperformance"
    //     }
    // }
 
  }
}