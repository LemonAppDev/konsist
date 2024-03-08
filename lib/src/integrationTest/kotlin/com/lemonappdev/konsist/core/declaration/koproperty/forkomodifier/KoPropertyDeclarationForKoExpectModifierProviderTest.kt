package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoExpectModifierProviderTest {
    @Test
    fun `property-has-no-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-expect-modifier")
                .properties()
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-expect-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkoexpectmodifierprovider/",
            fileName,
        )
}
