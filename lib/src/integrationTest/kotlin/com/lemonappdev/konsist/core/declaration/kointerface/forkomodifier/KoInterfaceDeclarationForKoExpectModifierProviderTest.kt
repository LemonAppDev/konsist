package com.lemonappdev.konsist.core.declaration.kointerface.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoExpectModifierProviderTest {
    @Test
    fun `interface-without-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-without-expect-modifier")
                .interfaces()
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo false
    }

    @Test
    fun `interface-with-expect-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-with-expect-modifier")
                .interfaces()
                .first()

        // then
        sut.hasExpectModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/forkomodifier/snippet/forkoexpectmodifierprovider/", fileName)
}
