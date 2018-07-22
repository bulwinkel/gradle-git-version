package com.bulwinkel.gitversion

import io.kotlintest.matchers.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

val plainSemanticVersions = listOf(
        "0.0.0",
        "1.0.1",
        "20000.56.30000"
)

val suffixes = listOf(
        "dev",
        "qa",
        "qa1",
        "qa2",
        "qa3",
        "uat",
        "alpha",
        "beta"
)

val invalidOrUnsupportedSemanticVersion = listOf(
        "",
        "1",
        "1.",
        "1..",
        "10..",
        "2.2",
        "2.2.",
        "2.2.."
)

class DefaultVersionRegexTests: Spek({
    on("plain semantic version") {
        plainSemanticVersions.forEach {
            it("should match $it") {
                defaultVersionRegex.matches(it) shouldBe true
            }
        }
    }

    on("suffixed semantic version") {
        suffixes.forEach { suffix ->
            plainSemanticVersions.forEach { semVer ->
                val v = "$semVer-$suffix"
                it("should match $v") {
                    defaultVersionRegex.matches(v) shouldBe true
                }
            }
        }
    }

    on("invalid or unsupported semantic version") {
        invalidOrUnsupportedSemanticVersion.forEach {
            it("should not match $it") {
                defaultVersionRegex.matches(it) shouldBe false
            }
        }
    }
})