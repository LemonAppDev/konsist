package com.lemonappdev.konsist.core.declaration.koimportdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForWildcardTest {
    @Test
    fun `import-with-wildcard`() {
        // given
        val sut = getSnippetFile("import-with-wildcard")
            .imports()
            .first()

        // then
        sut.isWildcard shouldBeEqualTo true
    }

    @Test
    fun `import-without-wildcard`() {
        // given
        val sut = getSnippetFile("import-without-wildcard")
            .imports()
            .first()

        // then
        sut.isWildcard shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportdeclaration/snippet/forwildcard/", fileName)
}
