package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForExtensionTest {
    @Test
    fun `function-is-extension`() {
        // given
        val sut = getSnippetFile("function-is-extension")
            .functions()
            .first()

        // then
        sut.isExtension() shouldBeEqualTo true
    }

    @Test
    fun `function-is-not-extension`() {
        // given
        val sut = getSnippetFile("function-is-not-extension")
            .functions()
            .first()

        // then
        sut.isExtension() shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forextension/", fileName)
}
