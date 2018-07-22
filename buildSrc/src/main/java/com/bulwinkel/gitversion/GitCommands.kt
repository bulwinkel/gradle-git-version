package com.bulwinkel.gitversion

import com.bulwinkel.cl.git.Git
import com.bulwinkel.cl.git.HEAD
import com.bulwinkel.cl.git.count
import com.bulwinkel.cl.git.describe
import com.bulwinkel.cl.git.get
import com.bulwinkel.cl.git.list
import com.bulwinkel.cl.git.match
import com.bulwinkel.cl.git.revList
import com.bulwinkel.cl.git.tag
import com.bulwinkel.cl.git.tags
import com.bulwinkel.cl.readLines

val defaultVersionRegex = Regex("(\\d+)\\.(\\d+)\\.(\\d+)([-\\w]*)")

fun Git.describeLatestVersionTag(versionRegex: Regex = defaultVersionRegex) : String {
    val allTags = tag.list.readLines()
    val latestVersionTag = allTags
            .lastOrNull { it.matches(versionRegex) } ?: ""

    if (latestVersionTag.isEmpty()) return ""

    return describe.tags.match(latestVersionTag).readLines().firstOrNull() ?: ""
}

fun Git.totalReachableCommits() : Int {
    val headCount = revList[HEAD].count.readLines().firstOrNull()?.toInt() ?: 0
    val headExcludingMaster = revList["HEAD..master"].count.readLines().firstOrNull()?.toInt() ?: 0
    return headCount - headExcludingMaster
}