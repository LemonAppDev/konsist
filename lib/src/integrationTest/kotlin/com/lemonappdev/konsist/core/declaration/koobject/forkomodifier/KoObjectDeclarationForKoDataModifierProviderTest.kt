package com.lemonappdev.konsist.core.declaration.koobject.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoDataModifierProviderTest {
    @Test
    fun `object-without-data-modifier`() {
        // given
        val sut =
            getSnippetFile("object-without-data-modifier")
                .objects()
                .first()

        // then
        sut.hasDataModifier shouldBeEqualTo false
    }

    @Test
    fun `data-object`() {
        // given
        val sut =
            getSnippetFile("data-object")
                .objects()
                .first()

        // then
        sut.hasDataModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/forkomodifier/snippet/forkodatamodifierprovider/", fileName)
}
