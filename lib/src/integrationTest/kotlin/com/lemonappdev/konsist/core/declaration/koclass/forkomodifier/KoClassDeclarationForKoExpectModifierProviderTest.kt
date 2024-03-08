package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoExpectModifierProviderTest {
    @Test
    fun `class-without-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-expect-modifier")
                .classes(includeNested = true)
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo false
    }

    @Test
    fun `expect-class`() {
        // given
        val sut =
            getSnippetFile("expect-class")
                .classes(includeNested = true)
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoexpectmodifierprovider/", fileName)
}
