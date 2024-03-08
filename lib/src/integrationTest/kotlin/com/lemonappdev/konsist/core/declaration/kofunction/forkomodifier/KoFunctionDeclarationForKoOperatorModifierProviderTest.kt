package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoOperatorModifierProviderTest {
    @Test
    fun `function-without-operator-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-operator-modifier")
                .functions()
                .first()

        // then
        sut.hasOperatorModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-operator-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-operator-modifier")
                .functions()
                .first()

        // then
        sut.hasOperatorModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkooperatormodifierprovider/", fileName)
}
