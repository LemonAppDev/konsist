package com.lemonappdev.konsist.core.declaration.koconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoConstantDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `enum-const-containing-declaration`() {
        // given
        val sut = getSnippetFile("enum-const-containing-declaration")
            .classes()
            .first()
            .constants
            .first()

        // then
        assertSoftly(sut) {
            containingDeclaration shouldNotBeEqualTo null
            (containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstant/snippet/forkocontainingdeclarationprovider/", fileName)
}
