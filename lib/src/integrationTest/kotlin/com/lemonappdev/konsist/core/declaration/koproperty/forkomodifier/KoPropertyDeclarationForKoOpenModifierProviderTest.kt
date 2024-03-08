package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoOpenModifierProviderTest {
    @Test
    fun `property-has-no-open-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-no-open-modifier")
                .properties()
                .first()

        // then
        sut.hasOpenModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-open-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-open-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasOpenModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkoopenmodifierprovider/",
            fileName,
        )
}
