package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoAnnotationProviderTest {
    @ParameterizedTest
    @MethodSource("provideClassesForNoAnnotation")
    fun `class-parent-has-no-annotation`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            annotations shouldBeEqualTo emptyList()
            numAnnotations shouldBeEqualTo 0
            countAnnotations { type -> type.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(setOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            hasAnnotation { type -> type.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { type -> type.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo false
            hasAnnotationOf(emptySet()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(listOf(SampleAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(SampleAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForNoAnnotation")
    fun `interface-parent-has-no-annotation`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            annotations shouldBeEqualTo emptyList()
            numAnnotations shouldBeEqualTo 0
            countAnnotations { type -> type.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(setOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            hasAnnotation { type -> type.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { type -> type.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo false
            hasAnnotationOf(emptySet()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(listOf(SampleAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(SampleAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForNoAnnotation")
    fun `object-parent-has-no-annotation`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            annotations shouldBeEqualTo emptyList()
            numAnnotations shouldBeEqualTo 0
            countAnnotations { type -> type.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(setOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            hasAnnotation { type -> type.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { type -> type.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo false
            hasAnnotationOf(emptySet()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(listOf(SampleAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(SampleAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkoannotationprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClassesForNoAnnotation() =
            listOf(
                arguments("class-with-parent-class-from-file"),
                arguments("class-with-generic-parent-class-from-file"),
                arguments("class-with-parametrized-parent-class-from-file"),
                arguments("class-with-parametrized-and-generic-parent-class-from-file"),
                arguments("class-with-parent-interface-from-file"),
                arguments("class-with-generic-parent-interface-from-file"),
                arguments("class-with-parent-by-delegation-from-file"),
                arguments("class-with-multiline-parent-from-file"),
                arguments("class-with-parent-class-from-import"),
                arguments("class-with-generic-parent-class-from-import"),
                arguments("class-with-parametrized-parent-class-from-import"),
                arguments("class-with-parametrized-and-generic-parent-class-from-import"),
                arguments("class-with-parent-interface-from-import"),
                arguments("class-with-generic-parent-interface-from-import"),
                arguments("class-with-parent-by-delegation-from-import"),
                arguments("class-with-multiline-parent-from-import"),
                arguments("class-with-external-parent-class"),
                arguments("class-with-generic-external-parent-class"),
                arguments("class-with-parametrized-external-parent-class"),
                arguments("class-with-parametrized-and-generic-external-parent-class"),
                arguments("class-with-external-parent-interface"),
                arguments("class-with-generic-external-parent-interface"),
                arguments("class-with-external-parent-by-delegation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoAnnotation() =
            listOf(
                arguments("interface-with-parent-interface-from-file"),
                arguments("interface-with-generic-parent-interface-from-file"),
                arguments("interface-with-parent-interface-from-import"),
                arguments("interface-with-generic-parent-interface-from-import"),
                arguments("interface-with-external-parent-interface"),
                arguments("interface-with-generic-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForNoAnnotation() =
            listOf(
                arguments("object-with-parent-class-from-file"),
                arguments("object-with-generic-parent-class-from-file"),
                arguments("object-with-parametrized-parent-class-from-file"),
                arguments("object-with-parametrized-and-generic-parent-class-from-file"),
                arguments("object-with-parent-interface-from-file"),
                arguments("object-with-generic-parent-interface-from-file"),
                arguments("object-with-multiline-parent-from-file"),
                arguments("object-with-parent-class-from-import"),
                arguments("object-with-generic-parent-class-from-import"),
                arguments("object-with-parametrized-parent-class-from-import"),
                arguments("object-with-parametrized-and-generic-parent-class-from-import"),
                arguments("object-with-parent-interface-from-import"),
                arguments("object-with-generic-parent-interface-from-import"),
                arguments("object-with-multiline-parent-from-import"),
                arguments("object-with-external-parent-class"),
                arguments("object-with-generic-external-parent-class"),
                arguments("object-with-parametrized-external-parent-class"),
                arguments("object-with-parametrized-and-generic-external-parent-class"),
                arguments("object-with-external-parent-interface"),
                arguments("object-with-generic-external-parent-interface"),
            )
    }
}
