package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoDataModifierProviderTest {
    @Test
    fun `class-without-data-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-data-modifier")
                .classes()
                .first()

        // then
        sut.hasDataModifier shouldBeEqualTo false
    }

    @Test
    fun `data-class`() {
        // given
        val sut =
            getSnippetFile("data-class")
                .classes()
                .first()

        // then
        sut.hasDataModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkodatamodifierprovider/", fileName)
}
