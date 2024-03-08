package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoInnerModifierProviderTest {
    @Test
    fun `class-without-inner-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-inner-modifier")
                .classes(includeNested = true)
                .first()

        // then
        sut.hasInnerModifier shouldBeEqualTo false
    }

    @Test
    fun `nested-inner-class`() {
        // given
        val sut =
            getSnippetFile("nested-inner-class")
                .classes(includeNested = true)
                .first { it.name == "InnerClass" }

        // then
        sut.hasInnerModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoinnermodifierprovider/", fileName)
}
