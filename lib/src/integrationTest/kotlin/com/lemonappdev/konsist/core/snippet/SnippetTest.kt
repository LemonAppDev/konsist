package com.lemonappdev.konsist.core.snippet

import com.lemonappdev.konsist.core.ext.sep
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class SnippetTest {
    @Test
    fun `every snippet is used in tests`() {
        // given
        val snippets = File("..$sep")
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
        val r3 = Regex("""arguments\(\s*"([^"]+)"""")
        val withGetSnippetMethod = snippetNamesFromFiles(r1, "getSnippetFile(\"", "\")")
        val withArgument = snippetNamesFromFiles(r2, "arguments(\"", "\"") + snippetNamesFromFiles(r3, "arguments(\n", "\"")

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
        val map = mutableMapOf<File, String>()

        val filePaths = File("src/integrationTest/..")
            .walk()
            .filter { it.isKotlinSnippetFile }

        val texts = File("src/integrationTest/..")
            .walk()
            .filter { it.isKotlinSnippetFile }
            .map { it.readText() }
            .toList()

        filePaths.forEachIndexed { index, s -> map[s] = texts[index] }

        // when
        val sut = validateKotlinCode(map)

        // then
        sut shouldBeEqualTo 0
    }

    private fun snippetNamesFromFiles(regex: Regex, prefix: String, suffix: String) =
        File("..$sep")
            .walk()
            .filter { it.isKotlinNotSnippetFile }
            .map { it.readText() }
            .flatMap { regex.findAll(it) }
            .map { it.value }
            .map { it.removePrefix(prefix) }
            .map { it.trimIndent() }
            .map { it.removePrefix("\"") }
            .map { it.removeSuffix(suffix) }

    private fun validateKotlinCode(map: Map<File, String>): Int {
        var counter = 0
        val commands1 = listOf(
            "kotlinc",
            File("src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt").absolutePath,
            "-include-runtime",
            "-d",
            "test.jar",
        )

        val builder = ProcessBuilder(commands1)
        builder.redirectErrorStream(true)

        val process1 = builder.start().waitFor()
        require(process1 == 0) {"TestData is invalid file."}

        val file = File("build/snippet-test.test-snippet.kt")

        map.forEach {
            file.writeText(it.value)

            val command2 = listOf(
                "kotlinc",
                "-cp",
                "test.jar",
                "-nowarn",
                file.absolutePath,
            )

            try {
                val processBuilder = ProcessBuilder(command2)
                processBuilder.redirectErrorStream(false)
                val process = processBuilder.start()

                val errorReader = BufferedReader(InputStreamReader(process.errorStream))
                val errorOutput = errorReader.readText()
                if (errorOutput.isNotEmpty()) {
                    counter++
                    println("${it.key} \n $errorOutput")
                }
            } catch (e: IOException) {
                e.printStackTrace()
                println(it.key)
            }

            file.delete()

            File("com/").deleteRecursively()
            File("META-INF/").deleteRecursively()
        }

        ProcessBuilder(
            "git",
            "clean",
            "-f",
        ).start()

        return counter
    }

    companion object {
        private val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(".kttxt")
        private val File.isKotlinNotSnippetFile: Boolean get() = isFile && !name.endsWith(".kttxt")
    }
}
