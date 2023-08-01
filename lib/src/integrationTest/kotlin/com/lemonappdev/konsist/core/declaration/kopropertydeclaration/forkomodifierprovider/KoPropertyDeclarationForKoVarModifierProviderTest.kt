package com.lemonappdev.konsist.core.declaration.kopropertydeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoVarModifierProviderTest {
    @Test
    fun `property-has-no-var-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-var-modifier")
            .properties()
            .first()

        // then
        sut.hasVarModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-var-modifier`() {
        // given
        val sut = getSnippetFile("property-has-var-modifier")
            .properties()
            .first()

        // then
        sut.hasVarModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopropertydeclaration/forkomodifierprovider/snippet/forkovarmodifierprovider/",
            fileName
        )
}
