package com.lemonappdev.konsist.api.ext.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileExtTest {
    @Test
    fun `file-has-two-annotations-of-type`() {
        // given
        val sut = getSnippetFile("file-has-two-annotations-of-type")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-suppress-annotation-without-import`() {
        // given
        val sut = getSnippetFile("file-has-suppress-annotation-without-import")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<Suppress>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("api/ext/container/snippet/", fileName)
}
