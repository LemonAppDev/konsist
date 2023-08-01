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

class KoPropertyDeclarationForKoAbstractModifierProviderTest {
    @Test
    fun `property-has-no-abstract-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-abstract-modifier")
            .properties()
            .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo false
    }

    @Test
    fun `property-has-abstract-modifier`() {
        // given
        val sut = getSnippetFile("property-has-abstract-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasAbstractModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopropertydeclaration/forkomodifierprovider/snippet/forkoabstractmodifierprovider/",
            fileName
        )
}
