package com.lemonappdev.konsist.core.declaration.kopropertydeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoValModifierProviderTest {
    @Test
    fun `property-has-no-val-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-val-modifier")
            .properties()
            .first()

        // then
        sut.hasValModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-val-modifier`() {
        // given
        val sut = getSnippetFile("property-has-val-modifier")
            .properties()
            .first()

        // then
        sut.hasValModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopropertydeclaration/forkomodifierprovider/snippet/forkovalmodifierprovider/",
            fileName,
        )
}
