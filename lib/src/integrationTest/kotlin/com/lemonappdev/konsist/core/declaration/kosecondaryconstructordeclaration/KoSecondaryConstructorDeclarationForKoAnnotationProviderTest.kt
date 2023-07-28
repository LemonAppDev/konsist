package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoAnnotationProviderTest {
    @Test
    fun `secondary-constructor-has-no-annotation`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-no-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.annotations.toList().size shouldBeEqualTo 0
            it.hasAnnotations() shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-annotation`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.annotations.toList().size shouldBeEqualTo 1
            it.hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            it.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            it.hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-two-annotations`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-two-annotations")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.annotations.toList().size shouldBeEqualTo 2
            it.hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            it.hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            it.hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it.hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation1::class) shouldBeEqualTo true
            it.hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-suppress-annotation-without-import`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-suppress-annotation-without-import")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.annotations.toList().size shouldBeEqualTo 1
            it.hasAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkoannotationprovider/", fileName)
}
