package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoSealedModifierProviderTest {
    @Test
    fun `interface-without-sealed-modifier`() {
        // given
        val sut = getSnippetFile("interface-without-sealed-modifier")
            .interfaces()
            .first()

        // then
        sut.hasSealedModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-with-sealed-modifier`() {
        // given
        val sut = getSnippetFile("interface-with-sealed-modifier")
            .interfaces()
            .first()

        // then
        sut.hasSealedModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/forkomodifierprovider/snippet/forkosealedmodifierprovider/", fileName)
}
