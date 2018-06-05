package com.bulwinkel.git

object Git {

    private fun exec(command: String) : List<String> {
        val p = Runtime.getRuntime().exec(command)

        val result = p.waitFor()
        if (result != 0) {
            return emptyList()
        }

        return try {
            p.inputStream.bufferedReader().readLines()
        } catch (e: Throwable) {
            emptyList()
        }
    }

    private fun git(command: String) : List<String> = exec("git $command")

    //region: Tag

    class Tag {

        private fun tag(command: String) : List<String> = git("tag $command")

        fun list() : List<String> = tag("--list")
    }

    val tag = Tag()

    //endregion

    //region: Describe

    class Describe {

        private fun describe(command: String) : List<String> = git("describe $command")

        fun match(pattern: String) : String = describe("--match $pattern").firstOrNull() ?: ""
    }

    val describe = Describe()

    //endregion
}