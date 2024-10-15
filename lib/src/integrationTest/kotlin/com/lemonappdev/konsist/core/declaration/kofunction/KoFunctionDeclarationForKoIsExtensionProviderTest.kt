package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoIsExtensionProviderTest {
    @Test
    fun `function-with-extension-type`() {
        // given
        val sut =
            getSnippetFile("function-with-extension-type")
                .functions()
                .first()

        // then
        sut.isExtension shouldBeEqualTo true
    }

    @Test
    fun `function-without-extension-type`() {
        // given
        val sut =
            getSnippetFile("function-without-extension-type")
                .functions()
                .first()

        // then
        sut.isExtension shouldBeEqualTo false
    }

    @Test
    fun `function-with-generic-extension-type`() {
        // given
        val sut =
            getSnippetFile("function-with-generic-extension-type")
                .functions()
                .first()

        // then
        sut.isExtension shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkoextensiontype/", fileName)
}
