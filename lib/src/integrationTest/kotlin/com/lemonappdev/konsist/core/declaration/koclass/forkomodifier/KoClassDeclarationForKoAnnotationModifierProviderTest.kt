package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoAnnotationModifierProviderTest {
    @Test
    fun `class-without-annotation-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-annotation-modifier")
                .classes()
                .first()

        // then
        sut.hasAnnotationModifier shouldBeEqualTo false
    }

    @Test
    fun `annotation-class`() {
        // given
        val sut =
            getSnippetFile("annotation-class")
                .classes()
                .first()

        // then
        sut.hasAnnotationModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoannotationmodifierprovider/", fileName)
}
