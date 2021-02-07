pipeline {
  agent any

  stages {

    stage('Test JUnit') {
      steps {
            figlet 'Test JUnit'
            execute('./mvnw clean compile -e')
            execute('./mvnw clean package -e')
          }
    }

    stage('Test Postman') {
        steps {
            figlet 'Test Postman'
            execute('./mvnw spring-boot:run &')
            sleep 20
            execute ("newman run https://raw.githubusercontent.com/usach-devops/TrabajoFinalM4Postman/develop/LabDevopsUnidad4.postman_collection.json -e ./postman/DevOpsLabUnidad4.postman_environment.json")
        }
    }

    stage('Test Selenium') {
        steps {
            figlet 'Test Selenium'
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