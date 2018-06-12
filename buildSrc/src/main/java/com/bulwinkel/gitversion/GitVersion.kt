package com.bulwinkel.gitversion

import com.bulwinkel.git.Git

class GitVersion {
    private val git = Git()
    val name: String by lazy { git.describeLatestVersionTag() }
    val buildNumber: Int by lazy { git.totalReachableCommits() }
}