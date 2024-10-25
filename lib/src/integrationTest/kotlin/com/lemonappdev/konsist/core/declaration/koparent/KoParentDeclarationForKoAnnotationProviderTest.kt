package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
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
    @MethodSource("provideClassesForTwoAnnotations")
    fun `class-parent-has-two-annotations`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            countAnnotations { type -> type.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            countAnnotations { type -> type.name == "SampleAnnotation1" } shouldBeEqualTo 1
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationWithName("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(setOf("SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    setOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            hasAnnotationsWithAllNames("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                ).shouldBeEqualTo(true)

            hasAnnotationsWithAllNames(listOf("SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotation { type -> type.name == "SampleAnnotation1" } shouldBeEqualTo true
            hasAnnotation { type -> type.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { type -> !type.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { type -> type.hasNameEndingWith("tion1") } shouldBeEqualTo false
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            hasAnnotationOf(listOf(SampleAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(listOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(SampleAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            hasAllAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
                arguments("class-with-parent-class-without-annotation"),
                arguments("class-with-parametrized-and-generic-parent-class-without-annotation"),
                arguments("class-with-parent-interface-without-annotation"),
                arguments("class-with-generic-parent-interface-without-annotation"),
                arguments("class-with-parent-by-delegation-without-annotation"),
                arguments("class-with-external-parent-class-without-annotation"),
                arguments("class-with-parametrized-and-generic-external-parent-class-without-annotation"),
                arguments("class-with-external-parent-interface-without-annotation"),
                arguments("class-with-generic-external-parent-interface-without-annotation"),
                arguments("class-with-external-parent-by-delegation-without-annotation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideClassesForTwoAnnotations() =
            listOf(
                arguments("class-with-parent-class-with-two-annotations"),
                arguments("class-with-parametrized-and-generic-parent-class-with-two-annotations"),
                arguments("class-with-parent-interface-with-two-annotations"),
                arguments("class-with-generic-parent-interface-with-two-annotations"),
                arguments("class-with-parent-by-delegation-with-two-annotations"),
                arguments("class-with-external-parent-class-with-two-annotations"),
                arguments("class-with-parametrized-and-generic-external-parent-class-with-two-annotations"),
                arguments("class-with-external-parent-interface-with-two-annotations"),
                arguments("class-with-generic-external-parent-interface-with-two-annotations"),
                arguments("class-with-external-parent-by-delegation-with-two-annotations"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoAnnotation() =
            listOf(
                arguments("interface-with-parent-interface-without-annotation"),
                arguments("interface-with-generic-parent-interface-without-annotation"),
                arguments("interface-with-external-parent-interface-without-annotation"),
                arguments("interface-with-generic-external-parent-interface-without-annotation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForTwoAnnotations() =
            listOf(
                arguments("interface-with-parent-interface-with-two-annotations"),
                arguments("interface-with-generic-parent-interface-with-two-annotations"),
                arguments("interface-with-external-parent-interface-with-two-annotations"),
                arguments("interface-with-generic-external-parent-interface-with-two-annotations"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForNoAnnotation() =
            listOf(
                arguments("object-with-parent-class-without-annotation"),
                arguments("object-with-parametrized-and-generic-parent-class-without-annotation"),
                arguments("object-with-parent-interface-without-annotation"),
                arguments("object-with-generic-parent-interface-without-annotation"),
                arguments("object-with-external-parent-class-without-annotation"),
                arguments("object-with-parametrized-and-generic-external-parent-class-without-annotation"),
                arguments("object-with-external-parent-interface-without-annotation"),
                arguments("object-with-generic-external-parent-interface-without-annotation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForTwoAnnotations() =
            listOf(
                arguments("object-with-parent-class-with-two-annotations"),
                arguments("object-with-parametrized-and-generic-parent-class-with-two-annotations"),
                arguments("object-with-parent-interface-with-two-annotations"),
                arguments("object-with-generic-parent-interface-with-two-annotations"),
                arguments("object-with-external-parent-class-with-two-annotations"),
                arguments("object-with-parametrized-and-generic-external-parent-class-with-two-annotations"),
                arguments("object-with-external-parent-interface-with-two-annotations"),
                arguments("object-with-generic-external-parent-interface-with-two-annotations"),
            )
    }
}
