package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoExpectModifierProviderTest {
    @Test
    fun `function-without-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-expect-modifier")
                .functions()
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-expect-modifier")
                .functions()
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkoexpectmodifierprovider/", fileName)
}
