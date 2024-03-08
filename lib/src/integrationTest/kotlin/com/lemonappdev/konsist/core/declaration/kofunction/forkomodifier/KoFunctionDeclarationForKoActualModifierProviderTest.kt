package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoActualModifierProviderTest {
    @Test
    fun `function-without-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-actual-modifier")
                .functions()
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-actual-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkoactualmodifierprovider/", fileName)
}
