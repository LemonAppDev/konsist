package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.Konsist.rootProjectPath
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.KotlinFileParser
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class SnippetKonsistTest {
    @Test
    fun `every snippet is used in tests`() {
        val r1 = Regex("""getSnippetFile\("(.+)"\)""")
        val r2 = Regex("""arguments\("(.+)"\)""")
        val r3 = Regex("""([^"]+)"[^"]+"""")


        val notUsedSnippets = mutableListOf<String>()

        val withGetSnippetMethod = snippetPackageScope
            .files()
            .map { it.text }
            .flatMap { r1.findAll(it) }
            .map { it.value }
            .map { it.removePrefix("getSnippetFile(\"") }
            .map { it.removeSuffix("\")") }

        val withArgument = snippetPackageScope
            .files()
            .map { it.text }
            .flatMap { r2.findAll(it) }
            .map { it.value }
            .map { it.removePrefix("arguments(\"") }
            .flatMap { r3.findAll(it) }
            .map { it.value}
            .map { it.substringBefore("\"") }

        val usedTestsNames = withGetSnippetMethod + withArgument

        // dotad dziala, zostala nam tylko logika do przetestowania czy jest ok



        (withGetSnippetMethod+withArgument).sorted().toSet() == snippets.sorted().toSet()



        notUsedSnippets shouldBeEqualTo emptyList()
    }

    companion object {
        val snippetPackageScope = Konsist.scopeFromTest(sourceSetName = "integrationTest")

        private val pathProvider: PathProvider by lazy { PathProvider.getInstance() }
        private val rootProjectPath = pathProvider.rootProjectPath
        private val File.isKotlinSnippetFile get() = isFile && name.endsWith(KotlinFileParser.KOTLIN_SNIPPET_FILE_EXTENSION)

        val snippets = File("$rootProjectPath/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/")
            .walk()
            .filter { it.isKotlinSnippetFile }
            .map { it.name.removeSuffix(".kttxt") }
    }
}
