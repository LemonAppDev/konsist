package com.lemonappdev.konsist.core.snippet

import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException

class SnippetTest {
    @Test
    fun `every snippet is used in tests`() {
        // given
        val snippets = File("../")
            .walk()
            .filter { it.isKotlinSnippetFile }

        val snippetPaths = snippets
            .map { it.path }
            .toList()

        val snippetNames = snippets
            .map { it.name.removeSuffix(".kttxt") }

        val snippetMap = mutableMapOf<String, String>()
        snippetNames.forEachIndexed { index, s -> snippetMap[s] = snippetPaths[index] }

        val r1 = Regex("""getSnippetFile\("(.+)"\)""")
        val r2 = Regex("""arguments\("([^"]+)"""")
        val withGetSnippetMethod = snippetNamesFromFiles(r1, "getSnippetFile(\"", "\")")
        val withArgument = snippetNamesFromFiles(r2, "arguments(\"", "\"")

        val snippetNamesUsedInTests = (withGetSnippetMethod + withArgument).toSet()

        // then
        val sut = snippetMap.keys.toSet() - snippetNamesUsedInTests

        assertSoftly {
            sut shouldBeEqualTo emptySet()
            require(sut.isEmpty()) { "Unused snippets: ${sut.map { snippetMap[it] }}" }
        }
    }

    @Test
    fun `every snippet has valid code`() {
        // given
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
        val sut = validateKotlinCode(map)

        // then
        sut shouldBeEqualTo 0

    }

    private fun snippetNamesFromFiles(regex: Regex, prefix: String, suffix: String) =
        File("../")
            .walk()
            .filter { it.isKotlinNotSnippetFile }
            .map { it.readText() }
            .flatMap { regex.findAll(it) }
            .map { it.value }
            .map { it.removePrefix(prefix) }
            .map { it.removeSuffix(suffix) }

    companion object {
        private val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(".kttxt")
        private val File.isKotlinNotSnippetFile: Boolean get() = isFile && !name.endsWith(".kttxt")
    }

    private fun validateKotlinCode(map: Map<String, String>): Int {
        var counter = 0
        val file = File("snippet test.kt")

        map.forEach {
            file.writeText(it.value)

            try {
                val processBuilder = ProcessBuilder("kotlinc", file.path)
                val process = processBuilder.start()

                val errorOutput = process.errorStream.bufferedReader().use { reader -> reader.readText() }
                if (errorOutput.isNotEmpty()) {
                    counter++
                    println(it.key)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                counter++
                println(it.key)
            }
            file.writeText("")
        }

        file.delete()
        return counter
    }
}
