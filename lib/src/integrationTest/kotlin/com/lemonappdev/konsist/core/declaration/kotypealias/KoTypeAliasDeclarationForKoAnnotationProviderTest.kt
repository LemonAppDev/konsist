package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoAnnotationProviderTest {
    @Test
    fun `typealias-has-no-annotation`() {
        // given
        val sut = getSnippetFile("typealias-has-no-annotation")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            annotations shouldBeEqualTo emptyList()
            numAnnotations shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-annotation`() {
        // given
        val sut = getSnippetFile("typealias-has-annotation")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-two-annotations`() {
        // given
        val sut = getSnippetFile("typealias-has-two-annotations")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-suppress-annotation-without-import`() {
        // given
        val sut = getSnippetFile("typealias-has-suppress-annotation-without-import")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            hasAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forkoannotationprovider/", fileName)
}
