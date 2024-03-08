package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoActualModifierProviderTest {
    @Test
    fun `property-has-no-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-actual-modifier")
                .properties()
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-actual-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkoactualmodifierprovider/",
            fileName,
        )
}
