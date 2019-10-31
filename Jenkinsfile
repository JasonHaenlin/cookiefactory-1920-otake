pipeline {
  agent any
  options {
    disableConcurrentBuilds()
    timeout(time: 1, unit: 'HOURS')
  }
  stages {
    stage('Checkout') {
      steps {
        echo 'Checkout'
      }
    }
    stage('Build') {
      steps {
        echo 'Clean Build'
        sh 'mvn clean compile'
      }
    }
    stage('Test JUnit') {
      steps {
        echo 'Testing'
        sh 'mvn test'
      }
    }
    stage('Test Mutation') {
      steps {
        echo 'PiTest Mutation'
        sh 'mvn org.pitest:pitest-maven:mutationCoverage'
      }
    }
    stage('Sonarqube') {
      agent any
      steps {
        echo 'Sonar Analysis'
        withSonarQubeEnv('Sonarqube_env') {
          sh 'mvn package sonar:sonar -Dsonar.pitest.mode=reuseReport'
        }

      }
    }
    stage('Quality Gate') {
      steps {
        timeout(time: 1, unit: 'HOURS') {
          waitForQualityGate true
        }
        echo 'passed'
      }
    }
    stage('Package') {
      steps {
        echo 'Packaging'
        sh 'mvn package -DskipTests'
      }
    }
    stage('Done') {
      steps {
        echo 'Done'
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'target/**/*', fingerprint: true
      junit 'target/surefire-reports/*.xml'
      echo 'JENKINS PIPELINE'
    }

    success {
      echo 'JENKINS PIPELINE SUCCESSFUL'
    }

    failure {
      echo 'JENKINS PIPELINE FAILED'
    }

    unstable {
      echo 'JENKINS PIPELINE WAS MARKED AS UNSTABLE'
    }

    changed {
      echo 'JENKINS PIPELINE STATUS HAS CHANGED SINCE LAST EXECUTION'
    }

  }
}
