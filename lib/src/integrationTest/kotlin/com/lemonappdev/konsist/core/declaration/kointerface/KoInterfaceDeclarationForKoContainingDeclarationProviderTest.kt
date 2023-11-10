package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `interface-with-file-parent-declaration`() {
        // given
        val sut = getSnippetFile("interface-with-file-parent-declaration")
            .interfaces()
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "interface-with-file-parent-declaration"
    }

    @Test
    fun `interface-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("interface-with-parent-declaration")
            .interfaces(includeNested = true)
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkocontainingdeclarationprovider/", fileName)
}
