package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.KoScope

object TestSnippetProvider {
    private const val TEST_SOURCE_SET_PATH = "lib/src/integrationTest/kotlin/com/lemonappdev/konsist/"
    private const val SNIPPET_EXTENSION = ".kttxt"

    fun getSnippetKoScope(
        snippetRelativePath: String,
        fileName: String,
    ): KoScope {
        val snippetPath = "$TEST_SOURCE_SET_PATH$snippetRelativePath$fileName$SNIPPET_EXTENSION"
        return Konsist.scopeFromFile(snippetPath)
    }
}
