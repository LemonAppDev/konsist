package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoInlineModifierProviderTest {
    @Test
    fun `function-without-inline-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-inline-modifier")
                .functions()
                .first()

        // then
        sut.hasInlineModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-inline-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-inline-modifier")
                .functions()
                .first()

        // then
        sut.hasInlineModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkoinlinemodifierprovider/", fileName)
}
