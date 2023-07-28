package com.lemonappdev.konsist.core.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationTest {
    @Test
    fun `annotation-to-string`() {
        // given
        val sut = getSnippetFile("annotation-to-string")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koannotationdeclaration/snippet/forgeneral/", fileName)
}
