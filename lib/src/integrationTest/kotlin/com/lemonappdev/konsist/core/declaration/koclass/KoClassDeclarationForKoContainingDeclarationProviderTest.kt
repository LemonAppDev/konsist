package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `class-with-file-parent-declaration`() {
        // given
        val sut = getSnippetFile("class-with-file-parent-declaration")
            .classes()
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "class-with-file-parent-declaration"
    }

    @Test
    fun `class-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("class-with-parent-declaration")
            .classes(includeNested = true)
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleInterface"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkocontainingdeclarationprovider/", fileName)
}
