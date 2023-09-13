package com.lemonappdev.konsist.core.declaration.koparameter

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
            it?.annotations shouldBeEqualTo emptyList()
            it?.numAnnotations shouldBeEqualTo 0
            it?.countAnnotations { annotation -> annotation.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            it?.hasAnnotation { annotation -> annotation.hasArguments() } shouldBeEqualTo false
            it?.hasAllAnnotations { annotation -> annotation.hasArguments() } shouldBeEqualTo true
            it?.hasAnnotations("SampleAnnotation") shouldBeEqualTo false
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
            it?.countAnnotations { it.name == "SampleAnnotation" } shouldBeEqualTo 1
            it?.countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            it?.hasAnnotation { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            it?.hasAnnotation { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            it?.hasAllAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
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
            it?.countAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            it?.countAnnotations { it.name == "SampleAnnotation1" } shouldBeEqualTo 1
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationWithName("SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.SampleAnnotation2",
            ).shouldBeEqualTo(true)
            it?.hasAnnotation { it.name == "SampleAnnotation1" } shouldBeEqualTo true
            it?.hasAnnotation { it.name == "OtherAnnotation1" } shouldBeEqualTo false
            it?.hasAllAnnotations { !it.hasArguments() } shouldBeEqualTo true
            it?.hasAllAnnotations { it.hasNameEndingWith("tion1") } shouldBeEqualTo false
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
        getSnippetKoScope("core/declaration/koparameter/snippet/forkoannotationprovider/", fileName)
}
