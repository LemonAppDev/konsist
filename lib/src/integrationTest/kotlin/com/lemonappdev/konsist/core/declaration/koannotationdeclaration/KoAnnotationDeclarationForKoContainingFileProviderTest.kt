package com.lemonappdev.konsist.core.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoContainingFileProviderTest {
    @Test
    fun `annotation-containing-file`() {
        // given
        val sut = getSnippetFile("annotation-containing-file")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotationdeclaration/snippet/forkocontainingfileprovider/", fileName)
}
