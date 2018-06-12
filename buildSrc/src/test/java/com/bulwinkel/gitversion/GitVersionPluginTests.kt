package com.bulwinkel.gitversion

import io.kotlintest.matchers.beGreaterThan
import io.kotlintest.matchers.match
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldNotBe
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GitVersionPluginTests : Spek({

    describe("GitVersionPlugin") {
        on("apply plugin") {
            it("should create a git version object and add it to the projects extensions properties") {
                val project = ProjectBuilder.builder().build()
                project.pluginManager.apply(GitVersionPlugin::class.java)

                val gitVersion = project.extensions.extraProperties["gitVersion"] as GitVersion
                gitVersion shouldNotBe null
                println("gitVersion.name = ${gitVersion.name}, gitVersion.buildNumber = ${gitVersion.buildNumber}")

                //language=RegExp
                gitVersion.name should match("\\d+\\.\\d+\\.\\d(-\\d-.*)?")
                gitVersion.buildNumber should beGreaterThan(0)
            }
        }
    }
})