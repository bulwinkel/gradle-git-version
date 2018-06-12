package com.bulwinkel.gitversion

import org.gradle.api.Plugin
import org.gradle.api.Project

open class GitVersionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.extraProperties["gitVersion"] = GitVersion()
    }
}