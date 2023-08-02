package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `function-without-parent`() {
        // given
        val sut = getSnippetFile("function-without-parent")
            .functions()
            .first()

        // then
        sut.containingDeclaration shouldBeEqualTo null
    }

    @Test
    fun `function-with-parent`() {
        // given
        val sut = getSnippetFile("function-with-parent")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            containingDeclaration shouldNotBeEqualTo null
            (containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkocontainingdeclarationprovider/", fileName)
}
