package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `parameter-parent-declaration`() {
        // given
        val sut = getSnippetFile("parameter-parent-declaration")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.containingDeclaration shouldNotBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forkocontainingdeclarationprovider/", fileName)
}
