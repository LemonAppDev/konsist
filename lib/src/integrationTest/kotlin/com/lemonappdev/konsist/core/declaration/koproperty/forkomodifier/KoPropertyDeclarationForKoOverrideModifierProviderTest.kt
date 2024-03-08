package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoOverrideModifierProviderTest {
    @Test
    fun `property-has-no-override-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-override-modifier")
                .properties()
                .first()

        // then
        sut.hasOverrideModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-override-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-override-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasOverrideModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkooverridemodifierprovider/",
            fileName,
        )
}
