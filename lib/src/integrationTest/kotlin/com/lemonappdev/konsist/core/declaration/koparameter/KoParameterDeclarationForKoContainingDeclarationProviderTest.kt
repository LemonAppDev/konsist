package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `parameter-in-constructor-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-parent-declaration")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.containingDeclaration shouldNotBeEqualTo null
    }

    @Test
    fun `parameter-in-function-invocation-parent-declaration`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-parent-declaration")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.containingDeclaration shouldNotBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkocontainingdeclarationprovider/", fileName)
}
