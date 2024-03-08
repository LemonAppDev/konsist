package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `enum-const-containing-declaration`() {
        // given
        val sut =
            getSnippetFile("enum-const-containing-declaration")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkocontainingdeclarationprovider/", fileName)
}
