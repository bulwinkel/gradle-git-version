package com.bulwinkel.gitversion

import com.bulwinkel.git.Git
import io.kotlintest.matchers.should
import io.kotlintest.matchers.startWith
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GitCommandsTests : Spek({
    on("describeLatestVersionTag") {
        it("should return the newest tag that matches the default version regex") {
            val versionName = Git.describeLatestVersionTag()
            println("versionName = $versionName")
            versionName should startWith("0.0.1")
        }
    }
})