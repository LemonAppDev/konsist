package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoAnnotationProviderTest {
    @Test
    fun `file-contains-no-annotation`() {
        // given
        val sut = getSnippetFile("file-contains-no-annotation")
            .files
            .first()

        // then
        assertSoftly(sut) {
            annotations.isEmpty() shouldBeEqualTo true
            numAnnotations shouldBeEqualTo 0
            hasAnnotations("SampleAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-two-annotations`() {
        // given
        val sut = getSnippetFile("file-contains-two-annotations")
            .files
            .first()

        // then
        assertSoftly(sut) {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation1", "SampleAnnotation2")
            numAnnotations shouldBeEqualTo 2
            hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("OtherAnnotation") shouldBeEqualTo false
            hasAnnotations("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.OtherAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-two-annotations-of-kclass`() {
        // given
        val sut = getSnippetFile("file-contains-two-annotations-of-kclass")
            .files
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-suppress-annotation-without-import`() {
        // given
        val sut = getSnippetFile("file-contains-suppress-annotation-without-import")
            .files
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationsOf(Suppress::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo false
            hasAnnotationsOf(Suppress::class, SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkoannotationprovider/",
        fileName,
    )
}
