package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoAnnotationProviderTest {
    @Test
    fun `parameter-has-no-annotation`() {
        // given
        val sut = getSnippetFile("parameter-has-no-annotation")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.annotations?.toList() shouldBeEqualTo emptyList()
            it?.numAnnotations shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-has-annotation`() {
        // given
        val sut = getSnippetFile("parameter-has-annotation")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 1
            it?.hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            it?.hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-has-two-annotations`() {
        // given
        val sut = getSnippetFile("parameter-has-two-annotations")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 2
            it?.hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            it?.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotations("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            it?.hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            it?.hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            it?.hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation1::class) shouldBeEqualTo true
            it?.hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-has-suppress-annotation-without-import`() {
        // given
        val sut = getSnippetFile("parameter-has-suppress-annotation-without-import")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 1
            it?.hasAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forkoannotationprovider/", fileName)
}
