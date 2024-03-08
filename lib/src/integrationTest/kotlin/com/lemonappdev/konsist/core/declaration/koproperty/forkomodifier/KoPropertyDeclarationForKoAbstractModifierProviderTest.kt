package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoAbstractModifierProviderTest {
    @Test
    fun `property-has-no-abstract-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-abstract-modifier")
                .properties()
                .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-abstract-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-abstract-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkoabstractmodifierprovider/",
            fileName,
        )
}
