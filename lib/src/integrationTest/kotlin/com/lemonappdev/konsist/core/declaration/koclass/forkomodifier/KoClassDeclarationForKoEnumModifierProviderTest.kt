package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoEnumModifierProviderTest {
    @Test
    fun `class-without-enum-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-enum-modifier")
                .classes()
                .first()

        // then
        sut.hasEnumModifier shouldBeEqualTo false
    }

    @Test
    fun `enum-class`() {
        // given
        val sut =
            getSnippetFile("enum-class")
                .classes()
                .first()

        // then
        sut.hasEnumModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoenummodifierprovider/", fileName)
}
