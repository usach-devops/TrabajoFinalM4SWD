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

  }
}