package com.lemonappdev.konsist.core.declaration.kointerface.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoActualModifierProviderTest {
    @Test
    fun `interface-without-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-without-actual-modifier")
                .interfaces()
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-with-actual-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-with-actual-modifier")
                .interfaces()
                .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/forkomodifier/snippet/forkoactualmodifierprovider/", fileName)
}
