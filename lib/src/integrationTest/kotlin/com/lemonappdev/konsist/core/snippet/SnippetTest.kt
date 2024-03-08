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
                .map { it.name.removeSuffix(FileExtension.KOTLIN_SNIPPET) }

        val snippetMap = mutableMapOf<String, String>()
        snippetNames.forEachIndexed { index, s -> snippetMap[s] = snippetPaths[index] }

        val r1 = Regex("""getSnippetFile\(\s*"(.+)"""")
        val r2 = Regex("""arguments\(\s*"([^"]+)"""")
        val r3 = Regex("""arguments\(\s*"([^"]+)"""")
        val withGetSnippetMethod = snippetNamesFromFiles(r1, "getSnippetFile(")
        val withArgument = snippetNamesFromFiles(r2, "arguments(") + snippetNamesFromFiles(r3, "arguments(\n")

        val snippetNamesUsedInTests = (withGetSnippetMethod + withArgument).toSet()

        // then
        val sut = snippetMap.keys.toSet() - snippetNamesUsedInTests

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
        private val File.isKotlinSnippetFile: Boolean get() = isFile && name.endsWith(FileExtension.KOTLIN_SNIPPET)
        private val File.isKotlinNotSnippetFile: Boolean get() = isFile && !name.endsWith(FileExtension.KOTLIN_SNIPPET)
    }
}
