package com.lemonappdev.konsist.core.declaration.kointerface.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoFunModifierProviderTest {
    @Test
    fun `interface-without-fun-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-without-fun-modifier")
                .interfaces()
                .first()

        // then
        sut.hasFunModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-with-fun-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-with-fun-modifier")
                .interfaces()
                .first()

        // then
        sut.hasFunModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/forkomodifier/snippet/forkofunmodifierprovider/", fileName)
}
