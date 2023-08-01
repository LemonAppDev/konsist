package com.lemonappdev.konsist.core.declaration.kopropertydeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPropertyDeclarationForKoActualModifierProviderTest {
    @Test
    fun `property-has-no-actual-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-actual-modifier")
            .properties()
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("property-has-actual-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopropertydeclaration/forkomodifierprovider/snippet/forkoactualmodifierprovider/",
            fileName
        )
}
