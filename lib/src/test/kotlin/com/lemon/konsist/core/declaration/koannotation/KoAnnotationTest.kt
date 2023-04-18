package com.lemon.konsist.core.declaration.koannotation

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationTest {
    @Test
    fun `annotation-type`() {
        // given
        val sut = getSnippetFile("annotation-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.type shouldBeEqualTo "SampleAnnotation"
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
        sut.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleAnnotation"
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/", fileName)
}
