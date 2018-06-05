package com.bulwinkel.git

import io.kotlintest.matchers.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GitTests : Spek({
    describe("Tag") {
        on("list") {
            it("should return a list of all tags") {
                Git.tag.list().isEmpty() shouldBe false
            }
        }
    }

    describe("Describe") {
        on("match") {
            it("should describe a tag given as the pattern") {
                val tag = "0.0.0"
                val description = Git.describe.match(tag)
                println("tag = $tag, description = $description")
                description.isNotBlank() shouldBe true
            }
        }
    }
})