package com.lemonappdev.konsist.core.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationTest {
    @Test
    fun `annotation-name`() {
        // given
        val sut = getSnippetFile("annotation-name")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.name shouldBeEqualTo "SampleAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("annotation-fully-qualified-name")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-without-import`() {
        // given
        val sut = getSnippetFile("annotation-fully-qualified-name-without-import")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleAnnotationWithoutImport"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotationdeclaration/snippet/", fileName)
}
