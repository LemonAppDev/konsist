package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `getter-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("getter-parent-declaration")
                .properties()
                .first()
                .getter

        // then
        (sut?.containingDeclaration as KoNameProvider).name shouldBeEqualTo "sampleProperty"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kogetter/snippet/forkocontainingdeclarationprovider/", fileName)
}
