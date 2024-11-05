package com.lemonappdev.konsist.core.snippet

import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeCreator
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeNode
import com.lemonappdev.konsist.core.util.FileExtension
import org.amshove.kluent.assertSoftly
import org.junit.jupiter.api.Test
import java.io.File

class SnippetTest {
    @Test
    fun `every snippet is used in tests`() {
        // given
        val snippets =
            File("../")
                .walk()
                .filter { it.isKotlinSnippetFile }
                // Filter out snippets used to generate documentation
                .filterNot { it.path.contains("lib/src/snippet/") }
                .toList()

        val snippetPaths: List<String> =
            snippets.map {
                val path = it.path.removePrefix("../lib/")
                val absolutePath = File(path).absolutePath

                "file://$absolutePath"
            }

        /*
        Matches calls to the `getSnippetFile` function, capturing the argument passed to it.
        It accounts for any spaces between the function name and the opening parenthesis,
        and captures the entire argument as a string enclosed in quotes e.g. `getSnippetFile("snippetName")`
         */
        val getSnippetFileRegex = Regex("""getSnippetFile\(\s*"(.+)"""")

        /*
        This regex matches calls to the `arguments` function, capturing the argument passed to it.
        It allows for spaces between the function name and the opening parenthesis,
        and captures the argument as a string within quotes e.g. `arguments("snippetName")`
         */
        val argumentsRegex = Regex("""arguments\(\s*"([^"]+)"""")

        val withGetSnippetMethod = snippetPathsFromFiles(getSnippetFileRegex, "getSnippetFile(")
        val withArgument = snippetPathsFromFiles(argumentsRegex, "arguments(")

        val snippetNamesUsedInTests = (withGetSnippetMethod + withArgument).toSet()

        // when
        val sut = snippetPaths.toSet() - snippetNamesUsedInTests

        // then
        assertSoftly {
            val asciiTreeNodes = sut.map { AsciiTreeNode(it, emptyList()) }

            val asciiTree =
                AsciiTreeCreator().invoke(
                    AsciiTreeNode(
                        "Unused snippets (${sut.size}):",
                        asciiTreeNodes,
                    ),
                )
            require(sut.isEmpty()) { asciiTree }
        }
    }

    private fun snippetPathsFromFiles(
        regex: Regex,
        prefix: String,
    ): List<String> {
        val getSnippetFileRegex = Regex("""getSnippetKoScope\(\s*"(.+?)"""")

        return File("../")
            .walk()
            .filter { it.isKotlinNotSnippetFile }
            .flatMap { file ->
                val fileText = file.readText()
                val filePath =
                    getSnippetFileRegex
                        .find(fileText)
                        ?.groupValues
                        ?.get(1)
                        .orEmpty()

                if (filePath.isNotEmpty()) {
                    regex
                        .findAll(fileText)
                        .map { match ->
                            val cleanedMatch =
                                match
                                    .value
                                    .removePrefix(prefix)
                                    .trim()
                                    .removePrefix("\"")
                                    .substringBefore("\"")

                            val testSourceSetPath = "src/integrationTest/kotlin/com/lemonappdev/konsist/"
                            val path = "$testSourceSetPath$filePath$cleanedMatch${FileExtension.KOTLIN_TEST_SNIPPET}"
                            val absolutePath = File(path).absolutePath
                            "file://$absolutePath"
                        }
                } else {
                    emptySequence()
                }
            }.toList()
    }

    companion object {
        private val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(FileExtension.KOTLIN_TEST_SNIPPET)
        private val File.isKotlinNotSnippetFile: Boolean get() = isFile && !name.endsWith(FileExtension.KOTLIN_TEST_SNIPPET)
    }
}
