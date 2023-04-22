pipeline {
    agent any
    tools{
        maven 'Maven 3.9.1'
    }

    stages {
        stage(' Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/srideep-indpro/devops-automation']])
                sh 'mvn clean install'
            }
        }
         stage(' Build Docker Image') {
            steps {
                script{
                    sh 'docker build -t srideep/devops-automation:1.0.0 .'
                }
            }
        }
        stage(' Push image to docker hub') {
            steps {
                script{
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                      sh 'docker login -u srideep -p ${dockerhubpwd}'
                    }
                    sh 'docker push srideep/devops-automation:1.0.0'
                }
            }
        }
    }
}
