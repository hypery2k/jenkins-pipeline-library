#!/usr/bin/groovy
package de.mare.ci.jenkins;

def isDevelopBranch() {
    env.BRANCH_NAME == 'develop'
}

def isProductionBranch() {
    env.BRANCH_NAME == 'master'
}

def isSupportBranch() {
    env.BRANCH_NAME.startsWith('support')
}

def isFeatureBranch() {
    env.BRANCH_NAME.startsWith('feature')
}
