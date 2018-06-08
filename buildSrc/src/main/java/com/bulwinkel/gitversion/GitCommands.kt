package com.bulwinkel.gitversion

import com.bulwinkel.git.Git

private val defaultVersionRegex = Regex("\\d+\\.\\d+\\.\\d+")

fun Git.describeLatestVersionTag(versionRegex: Regex = defaultVersionRegex) : String {
    val allTags = tag.list()
    println("allTags = $allTags")
    val latestVersionTag = allTags
            .lastOrNull { it.matches(versionRegex) } ?: ""
    println("latestVersionTag = $latestVersionTag")

    if (latestVersionTag.isEmpty()) return ""

    return describe.tags().match(latestVersionTag).exec().firstOrNull() ?: ""
}