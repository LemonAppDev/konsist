package com.lemon.konsist.core.declaration.koannotation

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.testdata.NonExistingAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
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

    @Test
    fun `annotation-represents-type`() {
        // given
        val sut = getSnippetFile("annotation-represents-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.run {
            representsType("SampleAnnotation") shouldBeEqualTo true
            representsType("NonExistingAnnotation") shouldBeEqualTo false
            representsType("com.lemon.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            representsType("com.lemon.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            representsType<SampleAnnotation>() shouldBeEqualTo true
            representsType<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/", fileName)
}
