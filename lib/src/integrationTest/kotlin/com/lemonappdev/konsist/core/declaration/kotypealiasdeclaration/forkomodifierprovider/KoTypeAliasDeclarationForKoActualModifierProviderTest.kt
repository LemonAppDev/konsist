package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeAliasDeclarationForKoActualModifierProviderTest {
    @Test
    fun `typealias-without-actual-modifier`() {
        // given
        val sut = getSnippetFile("typealias-without-actual-modifier")
            .typeAliases
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `typealias-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-actual-modifier")
            .typeAliases
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypealiasdeclaration/forkomodifierprovider/snippet/forkoactualmodifierprovider/",
            fileName
        )
}
