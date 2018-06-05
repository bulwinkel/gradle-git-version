package com.bulwinkel.gradle.git

import com.bulwinkel.git.Git
import com.bulwinkel.support.kotlin.firstOr
import com.bulwinkel.support.kotlin.orIfEmpty

private val defaultVersionRegex = Regex("\\d+\\.\\d+\\.\\d+")

//fun Git.latestVersionTag(versionRegex: Regex = ) : String {
//    val allTags = allTags() orIfEmpty { return "" }
//    val firstVersionTag = allTags
//            .firstOr("") { it.matches(versionRegex) }
//            .orIfEmpty { return "" }
//
//    return exec("git describe --match $firstVersionTag").firstOr("")
//}