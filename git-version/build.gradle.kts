import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.junit.platform.gradle.plugin.EnginesExtension
import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

group = "com.bulwinkel.gradle"
version = "0.0.0"

plugins {
    java
}

apply {
    plugin("kotlin")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    sourceSets["main"].java.srcDirs("../buildSrc/src/main")
}

val kotlinVersion: String by rootProject.extra

dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    testCompile("org.jetbrains.spek:spek-api:1.1.5") {
        exclude(group = "org.jetbrains.kotlin")
    }
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5") {
        exclude(group = "org.junit.platform")
        exclude(group = "org.jetbrains.kotlin")
    }
    testCompile("io.kotlintest:kotlintest:2.0.7")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}