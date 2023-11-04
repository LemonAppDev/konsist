package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoTailrecModifierProviderTest {
    @Test
    fun `function-without-tailrec-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-tailrec-modifier")
                .functions()
                .first()

        // then
        sut.hasTailrecModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-tailrec-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-tailrec-modifier")
                .functions()
                .first()

        // then
        sut.hasTailrecModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkotailrecmodifierprovider/", fileName)
}
