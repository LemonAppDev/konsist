package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoFinalModifierProviderTest {
    @Test
    fun `function-without-final-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-final-modifier")
                .functions()
                .first()

        // then
        sut.hasFinalModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-final-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-final-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasFinalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkofinalmodifierprovider/", fileName)
}
