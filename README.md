# Jenkins Pipeline Library

[![Build Status](https://travis-ci.org/hypery2k/jenkins-pipeline-library.svg?branch=master)](https://travis-ci.org/hypery2k/jenkins-pipeline-library)
[![Build Status](https://martinreinhardt-online.de/jenkins/buildStatus/icon?job=OSS/jenkins-pipeline-library/master)](https://martinreinhardt-online.de/jenkins/blue/organizations/jenkins/OSS%2Fjenkins-pipeline-library/branches/)

## Why

An Shared Library is defined with a name, a source code retrieval method such as by SCM, and optionally a default version. The name should be a short identifier as it will be used in scripts.

The version could be anything understood by that SCM; for example, branches, tags, and commit hashes all work for Git. You may also declare whether scripts need to explicitly request that library (detailed below), or if it is present by default. Furthermore, if you specify a version in Jenkins configuration, you can block scripts from selecting a different version.


## Usage

### <a name="nodejs"></a> NodeJS

To ease the support of multiple node versions
```
#!/usr/bin/groovy
@Library('my-build-library')
def nodeJS = new de.mare.ci.jenkins.NodeJS()


node {
  // npm install:
  nodeJS.nvm('install')
  // npm run build  
  nodeJS.nvmRun('build')
  // prop=abc npm run build
  nodeJS.nvmRun('build','prop=abc')
  // npm version -m "..."
  nodeJS.nvm("version -m\"$COMMIT_MESSAGE\"")
  // npm publish
  nodeJS.nvm("publish")
}

```

### <a name="maven"></a> Maven
```
#!/usr/bin/groovy
@Library('my-build-library')
def maven = new de.mare.ci.jenkins.Maven()


node {
  echo maven.getProjectVersion()

}

```

### <a name="git"></a> Git
```
#!/usr/bin/groovy
@Library('my-build-library')
def git = new de.mare.ci.jenkins.Git()


node {
  if (git.isDevelopBranch()){

  }

}

```
