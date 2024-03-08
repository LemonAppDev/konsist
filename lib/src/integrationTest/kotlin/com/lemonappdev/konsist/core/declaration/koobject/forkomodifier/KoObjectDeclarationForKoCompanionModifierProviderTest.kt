package com.lemonappdev.konsist.core.declaration.koobject.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoCompanionModifierProviderTest {
    @Test
    fun `object-without-companion-modifier`() {
        // given
        val sut =
            getSnippetFile("object-without-companion-modifier")
                .objects()
                .first()

        // then
        sut.hasCompanionModifier shouldBeEqualTo false
    }

    @Test
    fun `companion-object`() {
        // given
        val sut =
            getSnippetFile("companion-object")
                .objects(includeNested = true)
                .first()

        // then
        sut.hasCompanionModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/forkomodifier/snippet/forkocompanionmodifierprovider/", fileName)
}
