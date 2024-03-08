package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoFinalModifierProviderTest {
    @Test
    fun `property-has-no-final-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-final-modifier")
                .properties()
                .first()

        // then
        sut.hasFinalModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-final-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-final-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasFinalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkofinalmodifierprovider/",
            fileName,
        )
}
