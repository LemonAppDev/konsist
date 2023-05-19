package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.KotlinFileParser
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class SnippetKonsistTest {
    @Test
    fun `every snippet is used in tests`() {
        // given
        val snippets = File("$rootProjectPath/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/")
            .walk()
            .filter { it.isKotlinSnippetFile }
            .map { it.name.removeSuffix(".kttxt") }
            .toSet()

        val r1 = Regex("""getSnippetFile\("(.+)"\)""")
        val r2 = Regex("""arguments\("([^"]+)"""")
        val withGetSnippetMethod = snippetNamesFromFiles(r1, "getSnippetFile(\"", "\")")
        val withArgument = snippetNamesFromFiles(r2, "arguments(\"", "\"")
        val snippetNamesUsedInTests = (withGetSnippetMethod + withArgument).toSet()

        // then
        val actual = snippets - snippetNamesUsedInTests
        actual shouldBeEqualTo emptySet()
    }

    private fun snippetNamesFromFiles(regex: Regex, prefix: String, suffix: String) = snippetPackageScope
        .files()
        .map { it.text }
        .flatMap { regex.findAll(it) }
        .map { it.value }
        .map { it.removePrefix(prefix) }
        .map { it.removeSuffix(suffix) }

    companion object {
        private val snippetPackageScope = Konsist.scopeFromTest(sourceSetName = "integrationTest")
        private val pathProvider: PathProvider by lazy { PathProvider.getInstance() }
        private val rootProjectPath = pathProvider.rootProjectPath
        private val File.isKotlinSnippetFile get() = isFile && name.endsWith(KotlinFileParser.KOTLIN_SNIPPET_FILE_EXTENSION)
    }
}
