import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.2.41"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}

plugins {
    java
    `kotlin-dsl`
}

group = "com.bulwinkel.gradle"
version = "0.0.0"

apply {
    plugin("kotlin")
}

val kotlinVersion: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}