package com.lemonappdev.konsist.core.declaration.koconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstantDeclarationForKoContainingFileProviderTest {
    @Test
    fun `enum-const-containing-file`() {
        // given
        val sut = getSnippetFile("enum-const-containing-file")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstant/snippet/forkocontainingfileprovider/", fileName)
}
