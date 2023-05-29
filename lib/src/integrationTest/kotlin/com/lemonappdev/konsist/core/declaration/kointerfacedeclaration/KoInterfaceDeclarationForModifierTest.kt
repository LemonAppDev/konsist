package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForModifierTest {
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

    @Test
    fun `interface-has-no-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-no-modifier")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasActualModifier() shouldBeEqualTo false
            hasExpectModifier() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/formodifier/".toNormalizedPath(), fileName)
}
