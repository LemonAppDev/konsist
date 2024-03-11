package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoAbstractModifierProviderTest {
    @Test
    fun `class-without-abstract-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-abstract-modifier")
                .classes()
                .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo false
    }

    @Test
    fun `abstract-class`() {
        // given
        val sut =
            getSnippetFile("abstract-class")
                .classes()
                .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoabstractmodifierprovider/", fileName)
}
