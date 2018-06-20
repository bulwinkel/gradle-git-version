import com.bulwinkel.gitversion.GitVersion
import com.bulwinkel.gitversion.GitVersionPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.junit.platform.gradle.plugin.EnginesExtension
import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

plugins {
    `java-library`
    `kotlin-dsl`
    id ("com.gradle.plugin-publish") version ("0.9.7")
}

apply {
    plugin("kotlin")
    plugin("com.bulwinkel.gradle.git-version")
}

val gitVersion: GitVersion by extra

group = "com.bulwinkel.gradle"
version = gitVersion.name

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    sourceSets["main"].java.srcDirs("../buildSrc/src/main/java")
    sourceSets["main"].resources.srcDirs("../buildSrc/src/main/resources")
}

val kotlinVersion: String by rootProject.extra

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(gradleApi())
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

//region: Publishing

pluginBundle {
    website = "https://github.com/bulwinkel/gradle-git-version"
    vcsUrl = website
    plugins.create("gitVersion") {
        id = "com.bulwinkel.gradle.git-version"
        displayName = "Git Version"
        description = "Exposes customizable version information to your project based on your git repository."
        tags = listOf(
                "git",
                "version",
                "versioning",
                "buildNumber",
                "versionName"
        )
    }
}

//endregion

//region: task config

// setup build task to print the version report
afterEvaluate {
    val build by tasks
    val versionReport by tasks
    build.finalizedBy(versionReport)
}

//endregion