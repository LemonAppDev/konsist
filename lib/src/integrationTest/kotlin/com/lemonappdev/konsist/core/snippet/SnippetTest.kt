package com.lemonappdev.konsist.core.snippet

import com.lemonappdev.konsist.core.util.FileExtension
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
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

        val snippetPaths =
            snippets
                .map { it.path }

        val snippetNames =
            snippets
                .map { it.name.removeSuffix(FileExtension.KOTLIN_TEST_SNIPPET) }

        val snippetMap = mutableMapOf<String, String>()
        snippetNames.forEachIndexed { index, s -> snippetMap[s] = snippetPaths[index] }

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

        val withGetSnippetMethod = snippetNamesFromFiles(getSnippetFileRegex, "getSnippetFile(")
        val withArgument = snippetNamesFromFiles(argumentsRegex, "arguments(")

        val snippetNamesUsedInTests = (withGetSnippetMethod + withArgument).toSet()

        // when
        val sut = snippetMap.keys.toSet() - snippetNamesUsedInTests

        // then
        assertSoftly {
            sut shouldBeEqualTo emptySet()
            require(sut.isEmpty()) { "Unused snippets: ${sut.map { snippetMap[it] }}" }
        }
    }

    private fun snippetNamesFromFiles(
        regex: Regex,
        prefix: String,
    ) = File("../")
        .walk()
        .filter { it.isKotlinNotSnippetFile }
        .map { it.readText() }
        .flatMap { regex.findAll(it) }
        .map { it.value }
        .map { it.removePrefix(prefix) }
        .map { it.trim() }
        .map { it.removePrefix("\"") }
        .map { it.substringBefore("\"") }

    companion object {
        private val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(FileExtension.KOTLIN_TEST_SNIPPET)
        private val File.isKotlinNotSnippetFile: Boolean get() = isFile && !name.endsWith(FileExtension.KOTLIN_TEST_SNIPPET)
    }
}
