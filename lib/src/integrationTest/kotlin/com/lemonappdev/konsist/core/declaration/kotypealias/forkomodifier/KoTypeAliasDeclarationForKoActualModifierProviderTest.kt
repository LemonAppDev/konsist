package com.lemonappdev.konsist.core.declaration.kotypealias.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoActualModifierProviderTest {
    @Test
    fun `typealias-without-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("typealias-without-actual-modifier")
                .typeAliases
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `typealias-has-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("typealias-has-actual-modifier")
                .typeAliases
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypealias/forkomodifier/snippet/forkoactualmodifierprovider/",
            fileName,
        )
}
