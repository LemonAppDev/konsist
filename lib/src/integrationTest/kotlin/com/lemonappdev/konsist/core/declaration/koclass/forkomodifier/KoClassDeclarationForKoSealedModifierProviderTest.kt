package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoSealedModifierProviderTest {
    @Test
    fun `class-without-sealed-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-sealed-modifier")
                .classes()
                .first()

        // then
        sut.hasSealedModifier shouldBeEqualTo false
    }

    @Test
    fun `sealed-class`() {
        // given
        val sut =
            getSnippetFile("sealed-class")
                .classes()
                .first()

        // then
        sut.hasSealedModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkosealedmodifierprovider/", fileName)
}
