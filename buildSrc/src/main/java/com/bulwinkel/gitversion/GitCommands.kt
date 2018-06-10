package com.bulwinkel.gitversion

import com.bulwinkel.git.Git
import com.bulwinkel.git.HEAD
import com.bulwinkel.git.count
import com.bulwinkel.git.describe
import com.bulwinkel.git.get
import com.bulwinkel.git.list
import com.bulwinkel.git.match
import com.bulwinkel.git.readLines
import com.bulwinkel.git.revList
import com.bulwinkel.git.tag
import com.bulwinkel.git.tags

private val defaultVersionRegex = Regex("\\d+\\.\\d+\\.\\d+")

fun Git.describeLatestVersionTag(versionRegex: Regex = defaultVersionRegex) : String {
    val allTags = tag.list.readLines()
    println("allTags = $allTags")
    val latestVersionTag = allTags
            .lastOrNull { it.matches(versionRegex) } ?: ""
    println("latestVersionTag = $latestVersionTag")

    if (latestVersionTag.isEmpty()) return ""

    return describe.tags.match(latestVersionTag).readLines().firstOrNull() ?: ""
}

fun Git.totalReachableCommits() : Int {
    val headCount = revList[HEAD].count.readLines().first().toInt()
    val headExcludingMaster = revList["HEAD..master"].count.readLines().first().toInt()
    return headCount - headExcludingMaster
}