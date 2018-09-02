package com.bulwinkel.cl.git

import com.bulwinkel.cl.Executable

const val HEAD = "HEAD"


data class Git(override val command: String = "git") : Executable

val git: Git get() = Git()


data class GitTag(override val command: String) : Executable

val Git.tag: GitTag
    get() = GitTag("$command tag")

val GitTag.list: GitTag
    get() = GitTag("$command --list")

fun GitTag.sort(order: String): GitTag = GitTag("$command --sort=$order")


data class GitDescribe(override val command: String = "describe") : Executable

val Git.describe: GitDescribe
    get() = GitDescribe("$command describe")

val GitDescribe.tags : GitDescribe
    get() = GitDescribe("$command --tags")

fun GitDescribe.match(pattern: String) = GitDescribe(
        "$command --match $pattern")


data class GitRevList(override val command: String) : Executable

val Git.revList: GitRevList
    get() = GitRevList("$command rev-list")

operator fun GitRevList.get(commitIds: String) = GitRevList(
        "$command $commitIds")

val GitRevList.count: GitRevList
    get() = GitRevList("$command --count")
