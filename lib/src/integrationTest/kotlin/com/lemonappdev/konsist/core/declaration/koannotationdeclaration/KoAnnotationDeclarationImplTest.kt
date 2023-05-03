package com.lemonappdev.konsist.core.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationImplTest {
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

    @Test
    fun `annotation-represents-type`() {
        // given
        val sut = getSnippetFile("annotation-represents-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        assertSoftly(sut) {
            representsType("SampleAnnotation") shouldBeEqualTo true
            representsType("NonExistingAnnotation") shouldBeEqualTo false
            representsType("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            representsType("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            representsTypeOf<SampleAnnotation>() shouldBeEqualTo true
            representsTypeOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotationdeclaration/snippet/", fileName)
}
