package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationTest {
    @Test
    fun `interface-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-actual-modifier")
            .interfaces()
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-expect-modifier")
            .interfaces()
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kointerface/snippet/", fileName)
}
