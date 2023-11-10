package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `setter-parent-declaration`() {
        // given
        val sut = getSnippetFile("setter-parent-declaration")
            .properties()
            .first()
            .setter

        // then
        (sut?.containingDeclaration as KoNameProvider).name shouldBeEqualTo "sampleProperty"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosetter/snippet/forkocontainingdeclarationprovider/", fileName)
}
