package com.bulwinkel.gitversion

import com.bulwinkel.gitversion.GitVersionPlugin.Companion.TASK_GROUP
import com.bulwinkel.gitversion.GitVersionPlugin.Companion.TASK_NAME_VERSION_REPORT
import io.kotlintest.matchers.beGreaterThan
import io.kotlintest.matchers.match
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.matchers.shouldNotBe
import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

fun GitVersion.assertValid() {
    val gitVersion = this
    gitVersion shouldNotBe null
    println("gitVersion.name = ${gitVersion.name}, gitVersion.buildNumber = ${gitVersion.buildNumber}")

    gitVersion.name should match(SEMANTIC_GIT_VERSION_PATTERN)
    gitVersion.buildNumber should beGreaterThan(0)
}

class GitVersionPluginTests : Spek({

    describe("GitVersionPlugin") {
        on("apply plugin") {
            it("should create a git version object and add it to the projects extensions properties") {
                val project = ProjectBuilder.builder().build()
                project.pluginManager.apply(GitVersionPlugin::class.java)

                val gitVersion = project.extensions.extraProperties["gitVersion"] as GitVersion
                gitVersion.assertValid()
            }

            it("should be able to be applied by its id") {
                val project = ProjectBuilder.builder().build()
                project.pluginManager.apply("com.bulwinkel.gradle.git-version")

                val gitVersion = project.extensions.extraProperties["gitVersion"] as GitVersion
                gitVersion.assertValid()
            }

            it("should create a task that outputs the current version information") {
                val project = ProjectBuilder.builder().build()
                project.pluginManager.apply("com.bulwinkel.gradle.git-version")
                val task = project.tasks.getByName(TASK_NAME_VERSION_REPORT)
                task shouldNotBe null
                task.group shouldEqual TASK_GROUP
            }
        }
    }
})