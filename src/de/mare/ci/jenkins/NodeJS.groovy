#!/usr/bin/groovy
package de.mare.ci.jenkins;

def nvm(runTarget, opts = null) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    sh """#!/bin/bash -e
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm use
        ${prefix}npm ${runTarget}
    """
}

def nvmRun(runTarget, opts = null) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    sh """#!/bin/bash -e
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm use
        ${prefix}npm run ${runTarget}
    """
}

def nvmNode(command, opts = null) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    sh """#!/bin/bash -e
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm use
        ${prefix}node ${command}
    """
}

def publishSnapshot(directory, buildNumber, name) {
    dir(directory) {
        // get current package version
        def currentVersion = sh(returnStdout: true, script: "npm version | grep \"{\" | tr -s ':' | tr '_' '-' | tr '/' '-' | cut -d \"'\" -f 4").trim()
        // add build number for maven-like snapshot
        def newVersion = "${currentVersion}-dev-${buildNumber}"
        // publish snapshot to NPM
        sh "npm version ${newVersion} --no-git-tag-version && npm publish --tag next"
    }
}

return this;
