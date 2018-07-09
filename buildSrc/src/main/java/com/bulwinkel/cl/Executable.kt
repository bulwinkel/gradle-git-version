package com.bulwinkel.cl

interface Executable {
    val command: String
}

fun Executable.readLines() : List<String> {
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
