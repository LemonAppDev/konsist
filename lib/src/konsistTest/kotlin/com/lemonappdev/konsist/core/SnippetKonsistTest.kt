// package com.lemonappdev.konsist.core
//
// import com.lemonappdev.konsist.api.Konsist
// import com.lemonappdev.konsist.core.filesystem.PathProvider
// import com.lemonappdev.konsist.core.util.KotlinFileParser
// import org.amshove.kluent.assertSoftly
// import org.amshove.kluent.shouldBeEqualTo
// import org.junit.jupiter.api.Test
// import java.io.File
//
// class SnippetKonsistTest {
//    @Test
//    fun `every snippet is used in tests`() {
//        // given
//        val snippets = File("$rootProjectPath/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/")
//            .walk()
//            .filter { it.isKotlinSnippetFile }
//
//        val snippetPaths = snippets
//            .map { it.path }
//            .toList()
//
//        val snippetNames = snippets
//            .map { it.name.removeSuffix(".kttxt") }
//
//        val snippetMap = mutableMapOf<String, String>()
//        snippetNames.forEachIndexed { index, s -> snippetMap[s] = snippetPaths[index] }
//
//        val r1 = Regex("""getSnippetFile\("(.+)"\)""")
//        val r2 = Regex("""arguments\("([^"]+)"""")
//        val withGetSnippetMethod = snippetNamesFromFiles(r1, "getSnippetFile(\"", "\")")
//        val withArgument = snippetNamesFromFiles(r2, "arguments(\"", "\"")
//
//        val snippetNamesUsedInTests = (withGetSnippetMethod + withArgument).toSet()
//
//        // then
//        val sut = snippetMap.keys.toSet() - snippetNamesUsedInTests
//
//        assertSoftly {
//            sut shouldBeEqualTo emptySet()
//            require(sut.isEmpty()) { "Unused snippets: ${sut.map { snippetMap[it] }}" }
//        }
//    }
//
//    private fun snippetNamesFromFiles(regex: Regex, prefix: String, suffix: String) = snippetPackageScope
//        .files()
//        .map { it.text }
//        .flatMap { regex.findAll(it) }
//        .map { it.value }
//        .map { it.removePrefix(prefix) }
//        .map { it.removeSuffix(suffix) }
//
//    companion object {
//        private val snippetPackageScope = Konsist.scopeFromTest(sourceSetName = "integrationTest")
//        private val pathProvider: PathProvider by lazy { PathProvider.getInstance() }
//        private val rootProjectPath = pathProvider.rootProjectPath
//        private val File.isKotlinSnippetFile get() = isFile && name.endsWith(KotlinFileParser.KOTLIN_SNIPPET_FILE_EXTENSION)
//    }
// }
