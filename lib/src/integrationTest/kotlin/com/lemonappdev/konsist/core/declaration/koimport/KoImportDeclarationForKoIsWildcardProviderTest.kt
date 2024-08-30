package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoIsWildcardProviderTest {
    @Test
    fun `import-without-wildcard`() {
        // given
        val sut =
            getSnippetFile("import-without-wildcard")
                .imports
                .first()

        // then
        sut.isWildcard shouldBeEqualTo false
    }

    @Test
    fun `import-with-wildcard`() {
        // given
        val sut =
            getSnippetFile("import-with-wildcard")
                .imports
                .first()

        // then
        sut.isWildcard shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/koimport/snippet/forkoiswildcardprovider/",
            fileName,
        )
}
