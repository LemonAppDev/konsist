package com.lemonappdev.konsist.core.snippet

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

fun main() {
    val map = mutableMapOf<String, String>()
    val filePaths = File("/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/container/kofile/snippet")
        .walk()
        .filter { it.isFile && it.name.endsWith(".kttxt") }
        .map { it.path.replace("kttxt", "kt") }
        .toList()


    val texts = File("/Users/natalia/IdeaProjects/konsist/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/container/kofile/snippet")
        .walk()
        .filter { it.isFile && it.name.endsWith(".kttxt") }
        .map { it.readText() }
        .toList()

    filePaths.forEachIndexed { index, s -> map[s] = texts[index] }

    println(validateKotlinCode(map))
}

private fun validateKotlinCode(map: Map<String, String>): Int {
    var counter = 0
    val file = File("snippet test.kt")

    map.forEach {
        file.writeText(it.value)

        try {
            val processBuilder = ProcessBuilder("kotlinc", file.path)
            val process = processBuilder.start()

            val errorReader = BufferedReader(InputStreamReader(process.errorStream))
            while (errorReader.readLine() != null) {
                counter++
//                println(it.key)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            counter++
//            println(it.key)
        }
        file.writeText("")
    }

    file.delete()
    return counter
}