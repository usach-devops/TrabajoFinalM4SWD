pipeline {
  agent any

  stages {

    stage('Test JUnit') {
      steps {
            execute('./mvnw test')
          }
    }

    stage('Test Postman') {
        steps {
            execute('./mvnw spring-boot:run &')
            sleep 20
            execute ("newman run https://raw.githubusercontent.com/usach-devops/TrabajoFinalM4Postman/develop/LabDevopsUnidad4.postman_collection.json -e ./postman/DevOpsLabUnidad4.postman_environment.json")
        }
    }

    stage('Test Selenium') {
        steps {
            execute( "./mvnw test -Dtest=SeleniumTST -DfailIfNoTests=false -e")
        }
    }
   }
}

def execute(command){
    if (isUnix()) {
        sh command
    }
    else{
        bat command
    }

}