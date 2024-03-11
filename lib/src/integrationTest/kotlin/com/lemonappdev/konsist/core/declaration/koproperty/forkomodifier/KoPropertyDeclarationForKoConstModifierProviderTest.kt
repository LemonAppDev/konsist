package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoConstModifierProviderTest {
    @Test
    fun `property-has-no-const-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-const-modifier")
                .properties()
                .first()

        // then
        sut.hasConstModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-const-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-const-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasConstModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkoconstmodifierprovider/",
            fileName,
        )
}
