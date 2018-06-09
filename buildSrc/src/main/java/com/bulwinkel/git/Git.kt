package com.bulwinkel.git

interface Executable {
    val command: String
}

fun Executable.readLines() : List<String> {
    val p = Runtime.getRuntime().exec(command)

    val result = p.waitFor()
    if (result != 0) {
        return emptyList()
    }

    return try {
        p.inputStream.bufferedReader().readLines()
    } catch (e: Throwable) {
        emptyList()
    }
}


data class Git(override val command: String = "git") : Executable

val git: Git = Git()


data class GitTag(override val command: String) : Executable

val Git.tag: GitTag get() = GitTag("$command tag")

val GitTag.list: GitTag get() = GitTag("$command --list")


data class GitDescribe(override val command: String = "describe") : Executable

val Git.describe: GitDescribe get() = GitDescribe("$command describe")

val GitDescribe.tags : GitDescribe get() = GitDescribe("$command --tags")

fun GitDescribe.match(pattern: String) = GitDescribe("$command --match $pattern")