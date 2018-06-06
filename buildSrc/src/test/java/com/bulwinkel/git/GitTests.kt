package com.bulwinkel.git

import io.kotlintest.matchers.beGreaterThan
import io.kotlintest.matchers.contain
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GitTests : Spek({

    val initialTag = "0.0.0"

    describe("Tag") {
        on("list") {
            it("should return a list of all tags") {
                val tags = Git.tag.list()
                tags.isEmpty() shouldBe false
                tags should contain(initialTag)
            }
        }
    }

    describe("Describe") {
        on("match") {
            it("should describe a tag given as the pattern") {
                val description = Git.describe.match(initialTag)
                val (version, commitCount, shortHash) = description.split("-")
                println("version = $version, commitCount = $commitCount, shortHash = $shortHash")

                description.isNotBlank() shouldBe true
                version shouldEqual initialTag
                commitCount.toInt() should beGreaterThan(0)
            }
        }
    }
})