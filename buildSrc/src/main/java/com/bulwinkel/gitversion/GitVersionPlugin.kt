package com.bulwinkel.gitversion

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer

open class GitVersionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val gitVersion = GitVersion()
        target.extensions.extraProperties["gitVersion"] = gitVersion
        target.tasks.createVersionReportTask(gitVersion)
    }

    private fun TaskContainer.createVersionReportTask(gitVersion: GitVersion) {
        create("versionReport") {
            group = "versioning"
            doLast {
                println("""

                    GitVersion Report
                    -----------------
                    gitVersion.name = ${gitVersion.name}
                    gitVersion.buildNumber = ${gitVersion.buildNumber}

                """.trimIndent())
            }
        }
    }
}