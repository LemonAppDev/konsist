package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoTopLevelProviderTest {
    @Test
    fun `interface-is-not-top-level`() {
        // given
        val sut = getSnippetFile("interface-is-not-top-level")
            .classes()
            .flatMap { it.interfaces() }
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo false
    }

    @Test
    fun `interface-is-top-level`() {
        // given
        val sut = getSnippetFile("interface-is-top-level")
            .interfaces()
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkotoplevelprovider/", fileName)
}
