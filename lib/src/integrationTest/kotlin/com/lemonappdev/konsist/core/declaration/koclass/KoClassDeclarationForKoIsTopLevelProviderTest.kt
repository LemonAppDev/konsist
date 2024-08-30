package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoIsTopLevelProviderTest {
    @Test
    fun `class-is-not-top-level`() {
        // given
        val sut =
            getSnippetFile("class-is-not-top-level")
                .classes(includeNested = true)
                .first { it.name == "SampleNestedClass" }

        // then
        sut.isTopLevel shouldBeEqualTo false
    }

    @Test
    fun `class-is-top-level`() {
        // given
        val sut =
            getSnippetFile("class-is-top-level")
                .classes()
                .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoistoplevelprovider/", fileName)
}
