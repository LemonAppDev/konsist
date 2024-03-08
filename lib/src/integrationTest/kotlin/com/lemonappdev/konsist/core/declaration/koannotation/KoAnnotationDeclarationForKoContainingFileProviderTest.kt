package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoContainingFileProviderTest {
    @Test
    fun `annotation-containing-file`() {
        // given
        val sut =
            getSnippetFile("annotation-containing-file")
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

    @Test
    fun `annotation-in-file-containing-file`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-containing-file")
                .files
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
        getSnippetKoScope("core/declaration/koannotation/snippet/forkocontainingfileprovider/", fileName)
}
