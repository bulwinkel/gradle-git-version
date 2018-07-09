package com.bulwinkel.cl.git

import com.bulwinkel.cl.readLines
import io.kotlintest.matchers.beEmpty
import io.kotlintest.matchers.beGreaterThan
import io.kotlintest.matchers.contain
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.matchers.shouldNot
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GitTests : Spek({

    val initialTag = "0.0.0"

    describe("Tag") {
        on("list") {
            it("should return a list of all tags") {
                val tags = git.tag.list.readLines()
                println("tags = $tags")
                tags.isEmpty() shouldBe false
                tags should contain(initialTag)
            }
        }
    }

    describe("Describe") {
        on("match") {
            it("should describe a tag given as the pattern") {
                val description = git.describe.tags.match(initialTag).readLines().firstOrNull() ?: ""
                val (version, commitCount, shortHash) = description.split("-")
                println("version = $version, commitCount = $commitCount, shortHash = $shortHash")

                description.isNotBlank() shouldBe true
                version shouldEqual initialTag
                commitCount.toInt() should beGreaterThan(0)
            }
        }
    }

    describe("rev-list") {
        on("HEAD") {
            it("should return a list all list all commit hashes from the current head") {
                val commits = git.revList[HEAD].readLines()
                println("commits = $commits")
                commits shouldNot beEmpty()
                commits.forEach { it.length shouldBe 40 }
            }
        }

        on("count") {
            it("should return the number of commits from the current head") {
                val commitCount = git.revList.count[HEAD].readLines().first().toInt()
                println("commitCount = $commitCount")
                commitCount should beGreaterThan(1)
            }
        }
    }
})