package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoValueModifierProviderTest {
    @Test
    fun `class-without-value-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-value-modifier")
                .classes()
                .first()

        // then
        sut.hasValueModifier shouldBeEqualTo false
    }

    @Test
    fun `value-class`() {
        // given
        val sut =
            getSnippetFile("value-class")
                .classes()
                .first()

        // then
        sut.hasValueModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkovaluemodifierprovider/", fileName)
}
