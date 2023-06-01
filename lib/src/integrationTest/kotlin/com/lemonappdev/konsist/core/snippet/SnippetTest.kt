package com.lemonappdev.konsist.core.snippet

import com.lemonappdev.konsist.core.ext.sep
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths

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

    @Test
    fun `every snippet has valid code`() {
        // given
        val files = File("src${sep}integrationTest$sep..")
            .walk()
            .filter { it.isKotlinSnippetFile }

        // then
        val sut = validateKotlinCode(files)
        sut shouldBeEqualTo ""
    }

    private fun validateKotlinCode(files: Sequence<File>): String {
        val commandConvertingTestDataToJar = listOf(
            "kotlinc",
            File("src${sep}integrationTest${sep}kotlin${sep}com${sep}lemonappdev${sep}konsist${sep}testdata${sep}TestData.kt").absolutePath,
            "-include-runtime",
            "-d",
            "test.jar",
        )

        val builderTestDataJar = ProcessBuilder(commandConvertingTestDataToJar)
        builderTestDataJar.redirectErrorStream(true)

        val processConvertingTestDataToJar = builderTestDataJar
            .start()
            .waitFor()

        require(processConvertingTestDataToJar == 0) { "TestData is invalid file." }

        Files.createDirectory(Paths.get("build").resolve("snippet-test$sep"))

        var commands = ""

        files.forEach {
            val snippetFileWithKt = it.copyTo(File("build${sep}snippet-test$sep${it.path.replace("kttxt", "kt")}"))

            commands += "kotlinc -cp test.jar -nowarn ${snippetFileWithKt.absolutePath};"
        }

        val processBuilder = ProcessBuilder("bash", "-c", commands)
        processBuilder.redirectErrorStream(false)

        val process = processBuilder.start()

        val errorReader = BufferedReader(InputStreamReader(process.errorStream))

        val errorOutput = errorReader.readText()

        File("com$sep").deleteRecursively()
        File("META-INF$sep").deleteRecursively()
        File("build${sep}snippet-test").deleteRecursively()

        ProcessBuilder(
            "git",
            "clean",
            "-f",
        ).start()

        return errorOutput
    }

    companion object {
        private val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(".kttxt")
        private val File.isKotlinNotSnippetFile: Boolean get() = isFile && !name.endsWith(".kttxt")
    }
}
