package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoInterfaceDeclarationForKoActualModifierProviderTest {
    @Test
    fun `interface-without-actual-modifier`() {
        // given
        val sut = getSnippetFile("interface-without-actual-modifier")
            .interfaces()
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-with-actual-modifier`() {
        // given
        val sut = getSnippetFile("interface-with-actual-modifier")
            .interfaces()
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/forkomodifierprovider/snippet/forkoactualmodifierprovider/", fileName)
}
