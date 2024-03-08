package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoActualModifierProviderTest {
    @Test
    fun `class-without-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-actual-modifier")
                .classes(includeNested = true)
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `actual-class`() {
        // given
        val sut =
            getSnippetFile("actual-class")
                .classes(includeNested = true)
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoactualmodifierprovider/", fileName)
}
