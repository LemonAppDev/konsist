package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `function-with-file-parent-declaration`() {
        // given
        val sut = getSnippetFile("function-with-file-parent-declaration")
            .functions()
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "function-with-file-parent-declaration"
    }

    @Test
    fun `function-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("function-with-parent-declaration")
            .functions(includeNested = true)
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkocontainingdeclarationprovider/", fileName)
}
