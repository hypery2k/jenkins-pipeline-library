#!/groovy
properties properties: [
        [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5', numToKeepStr: '10']],
        disableConcurrentBuilds()
]


timeout(10) {
    node {
        try {
            withEnv(["JAVA_HOME=${tool 'JDK8'}", "PATH+MAVEN=${tool 'Maven'}/bin:${env.JAVA_HOME}/bin"]) {

                def scriptDir = "./src/de/mare/ci/jenkins/"
                def end2endDir = "./test/end2end/de/mare/ci/jenkins/"

                println("Using script dir: " + scriptDir)


                stage('Checkout') {
                    checkout scm
                }

                stage('Build') {
                    sh "mvn clean install -DskipTests=true -DskipITs=true"
                    openTasks canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', high: 'FIXME', low: '', normal: 'TODO', pattern: '*/src/**/*', unHealthy: ''
                }

                stage('Unit-Test') {
                    sh "mvn test -DskipITs=true"
                    junit healthScaleFactor: 1.0, testResults: 'target/surefire-reports/TEST*.xml,target/failsafe-reports/TEST*.xml'
                }

                stage('End2End-Test') {
                    // point to exact source file
                    def nodeJsTest = load "${scriptDir}/NodeJS.groovy"

                    dir(end2endDir) {
                        dir('nodejs') {
                            nodeJsTest.nvm('install')
                        }
                    }
                }
            }

        } catch (e) {
      mail subject: "${jobName} (${buildNumber}): Error on build", to: 'appcms@martinreinhardt-online.de', body: "Please go to ${buildUrl}."
            throw e
        }
    }
}
