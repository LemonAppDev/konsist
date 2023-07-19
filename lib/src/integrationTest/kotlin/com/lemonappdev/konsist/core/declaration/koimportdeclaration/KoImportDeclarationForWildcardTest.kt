package com.lemonappdev.konsist.core.declaration.koimportdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForWildcardTest {
    @Test
    fun `import-with-wildcard`() {
        // given
        val sut = getSnippetFile("import-with-wildcard")
            .imports
            .first()

        // then
        sut.isWildcard shouldBeEqualTo true
    }

    @Test
    fun `import-without-wildcard`() {
        // given
        val sut = getSnippetFile("import-without-wildcard")
            .imports
            .first()

        // then
        sut.isWildcard shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koimportdeclaration/snippet/forwildcard/", fileName)
}
