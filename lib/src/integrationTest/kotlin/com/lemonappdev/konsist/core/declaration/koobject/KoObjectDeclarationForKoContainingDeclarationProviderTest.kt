package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `object-with-file-parent-declaration`() {
        // given
        val sut = getSnippetFile("object-with-file-parent-declaration")
            .objects()
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "object-with-file-parent-declaration"
    }

    @Test
    fun `object-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("object-with-parent-declaration")
            .objects(includeNested = true)
            .first()

        // then
        (sut.containingDeclaration as KoNameProvider).name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkocontainingdeclarationprovider/", fileName)
}
