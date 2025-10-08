package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoAnnotationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoAnnotation")
    fun `type-has-no-annotation`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.annotations shouldBeEqualTo emptyList()
            it?.numAnnotations shouldBeEqualTo 0
            it?.countAnnotations { annotation -> annotation.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo false
            it?.hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            it?.hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("sampleannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("sampleannotation", ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("sampleannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("sampleannotation"), ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("sampleannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("sampleannotation"), ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(setOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            it?.hasAnnotation { annotation -> annotation.hasArguments() } shouldBeEqualTo false
            it?.hasAllAnnotations { annotation -> annotation.hasArguments() } shouldBeEqualTo true
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo false
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            it?.hasAnnotationOf(SampleAnnotation::class) shouldBeEqualTo false
            it?.hasAnnotationOf(listOf(SampleAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(setOf(SampleAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForOneAnnotation")
    @Suppress("detekt.LongMethod")
    fun `type-has-annotation`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 1
            it?.countAnnotations { annotation -> annotation.name == "SampleAnnotation" } shouldBeEqualTo 1
            it?.countAnnotations { annotation -> annotation.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationWithName(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationWithName(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("sampleannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("sampleannotation", ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("otherannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("otherannotation", ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("sampleannotation", "otherannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("sampleannotation", "otherannotation", ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                ignoreCase = true
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = true
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.OtherAnnotation"
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = true
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("sampleannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("sampleannotation"), ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("otherannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("otherannotation"), ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(
                listOf("sampleannotation", "otherannotation"),
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(listOf("sampleannotation", "otherannotation"), ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.sampleannotation"
                ),
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.sampleannotation"
                ),
                ignoreCase = true
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.otherannotation"
                ),
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.otherannotation"
                ),
                ignoreCase = true
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.OtherAnnotation"
                )
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.sampleannotation",
                    "com.lemonappdev.konsist.testdata.otherannotation"
                ),
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.sampleannotation",
                    "com.lemonappdev.konsist.testdata.otherannotation"
                ),
                ignoreCase = true
            ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("sampleannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("sampleannotation", ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("sampleannotation", "otherannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                ignoreCase = true
            ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.sampleannotation",
                "com.lemonappdev.konsist.testdata.nonexistingannotation",
                ignoreCase = true
            ).shouldBeEqualTo(false)

            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("sampleannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("sampleannotation"), ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(
                listOf("sampleannotation", "otherannotation"),
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                listOf("com.lemonappdev.konsist.testdata.sampleannotation"),
                ignoreCase = false
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(
                listOf("com.lemonappdev.konsist.testdata.sampleannotation"),
                ignoreCase = true
            ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                )
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.sampleannotation",
                    "com.lemonappdev.konsist.testdata.nonexistingannotation",
                ),
                ignoreCase = true
            ).shouldBeEqualTo(false)
            it?.hasAnnotation { annotation -> annotation.hasNameStartingWith("Sample") } shouldBeEqualTo true
            it?.hasAnnotation { annotation -> annotation.hasNameStartingWith("Other") } shouldBeEqualTo false
            it?.hasAllAnnotations { annotation -> annotation.hasNameStartingWith("Sample") } shouldBeEqualTo true
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(SampleAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(listOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(SampleAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(setOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(SampleAnnotation::class, NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(SampleAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTwoAnnotations")
    @Suppress("detekt.LongMethod")
    fun `type-has-two-annotations`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 2
            it?.countAnnotations { type -> type.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            it?.countAnnotations { type -> type.name == "SampleAnnotation1" } shouldBeEqualTo 1
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationWithName("SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(setOf("SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    setOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("SampleAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                ).shouldBeEqualTo(true)

            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            it
                ?.hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                        "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                    ),
                ).shouldBeEqualTo(true)
            it?.hasAnnotation { type -> type.name == "SampleAnnotation1" } shouldBeEqualTo true
            it?.hasAnnotation { type -> type.name == "OtherAnnotation1" } shouldBeEqualTo false
            it?.hasAllAnnotations { type -> !type.hasArguments() } shouldBeEqualTo true
            it?.hasAllAnnotations { type -> type.hasNameEndingWith("tion1") } shouldBeEqualTo false
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationOf(SampleAnnotation1::class) shouldBeEqualTo true
            it?.hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAnnotationOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(SampleAnnotation1::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(listOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(SampleAnnotation1::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(setOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(SampleAnnotation1::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(listOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkoannotationrovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoAnnotation() =
            listOf(
                arguments("kotlin-type-has-no-annotation"),
                arguments("class-type-has-no-annotation"),
                arguments("interface-type-has-no-annotation"),
                arguments("object-type-has-no-annotation"),
                arguments("function-type-has-no-annotation"),
                arguments("import-alias-type-has-no-annotation"),
                arguments("typealias-type-has-no-annotation"),
                arguments("generic-type-has-no-annotation"),
                arguments("type-parameter-has-no-annotation"),
                arguments("external-type-has-no-annotation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForOneAnnotation() =
            listOf(
                arguments("kotlin-type-has-annotation"),
                arguments("class-type-has-annotation"),
                arguments("interface-type-has-annotation"),
                arguments("object-type-has-annotation"),
                arguments("function-type-has-annotation"),
                arguments("import-alias-type-has-annotation"),
                arguments("typealias-type-has-annotation"),
                arguments("generic-type-has-annotation"),
                arguments("type-parameter-has-annotation"),
                arguments("external-type-has-annotation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTwoAnnotations() =
            listOf(
                arguments("kotlin-type-has-two-annotations"),
                arguments("class-type-has-two-annotations"),
                arguments("interface-type-has-two-annotations"),
                arguments("object-type-has-two-annotations"),
                arguments("function-type-has-two-annotations"),
                arguments("import-alias-type-has-two-annotations"),
                arguments("typealias-type-has-two-annotations"),
                arguments("generic-type-has-two-annotations"),
                arguments("type-parameter-has-two-annotations"),
                arguments("external-type-has-two-annotations"),
            )
    }
}
