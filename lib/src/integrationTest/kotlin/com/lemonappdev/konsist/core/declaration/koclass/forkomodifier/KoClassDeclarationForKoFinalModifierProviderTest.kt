package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoFinalModifierProviderTest {
    @Test
    fun `class-without-final-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-final-modifier")
                .classes()
                .first()

        // then
        sut.hasFinalModifier shouldBeEqualTo false
    }

    @Test
    fun `final-class`() {
        // given
        val sut =
            getSnippetFile("final-class")
                .classes()
                .first()

        // then
        sut.hasFinalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkofinalmodifierprovider/", fileName)
}
