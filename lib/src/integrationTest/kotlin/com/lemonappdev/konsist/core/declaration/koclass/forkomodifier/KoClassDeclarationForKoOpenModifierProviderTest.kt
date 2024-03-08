package com.lemonappdev.konsist.core.declaration.koclass.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoOpenModifierProviderTest {
    @Test
    fun `class-without-open-modifier`() {
        // given
        val sut =
            getSnippetFile("class-without-open-modifier")
                .classes()
                .first()

        // then
        sut.hasOpenModifier shouldBeEqualTo false
    }

    @Test
    fun `open-class`() {
        // given
        val sut =
            getSnippetFile("open-class")
                .classes()
                .first()

        // then
        sut.hasOpenModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/forkomodifier/snippet/forkoopenmodifierprovider/", fileName)
}
