package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.FixtureAnnotation
import com.lemonappdev.konsist.testdata.FixtureAnnotation1
import com.lemonappdev.konsist.testdata.FixtureAnnotation2
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
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
            it?.hasAnnotationWithName("FixtureAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("fixtureannotation", ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("FixtureAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("FixtureAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(setOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            it?.hasAnnotation { annotation -> annotation.hasArguments() } shouldBeEqualTo false
            it?.hasAllAnnotations { annotation -> annotation.hasArguments() } shouldBeEqualTo true
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo false
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            it?.hasAnnotationOf(FixtureAnnotation::class) shouldBeEqualTo false
            it?.hasAnnotationOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
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
            it?.countAnnotations { annotation -> annotation.name == "FixtureAnnotation" } shouldBeEqualTo 1
            it?.countAnnotations { annotation -> annotation.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationWithName(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationWithName(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationWithName("FixtureAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("fixtureannotation", ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("otherannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("otherannotation", ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName("FixtureAnnotation", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("fixtureannotation", "otherannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName("fixtureannotation", "otherannotation", ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.FixtureAnnotation") shouldBeEqualTo true
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    ignoreCase = true,
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.OtherAnnotation") shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.otherannotation",
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.otherannotation",
                    ignoreCase = true,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                    "com.lemonappdev.konsist.testdata.OtherAnnotation",
                ).shouldBeEqualTo(true)
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.otherannotation",
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.otherannotation",
                    ignoreCase = true,
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("FixtureAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("otherannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("otherannotation"), ignoreCase = true) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("FixtureAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            it
                ?.hasAnnotationWithName(
                    listOf("fixtureannotation", "otherannotation"),
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it?.hasAnnotationWithName(listOf("fixtureannotation", "otherannotation"), ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation")) shouldBeEqualTo true
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.fixtureannotation",
                    ),
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.fixtureannotation",
                    ),
                    ignoreCase = true,
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.OtherAnnotation")) shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.otherannotation",
                    ),
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.otherannotation",
                    ),
                    ignoreCase = true,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                        "com.lemonappdev.konsist.testdata.OtherAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.fixtureannotation",
                        "com.lemonappdev.konsist.testdata.otherannotation",
                    ),
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.fixtureannotation",
                        "com.lemonappdev.konsist.testdata.otherannotation",
                    ),
                    ignoreCase = true,
                ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("FixtureAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("fixtureannotation", ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("FixtureAnnotation", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("fixtureannotation", "otherannotation", ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation") shouldBeEqualTo true
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    ignoreCase = true,
                ).shouldBeEqualTo(true)
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.nonexistingannotation",
                    ignoreCase = true,
                ).shouldBeEqualTo(false)

            it?.hasAnnotationsWithAllNames(listOf("FixtureAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("FixtureAnnotation", "OtherAnnotation")) shouldBeEqualTo false
            it
                ?.hasAnnotationsWithAllNames(
                    listOf("fixtureannotation", "otherannotation"),
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation")) shouldBeEqualTo true
            it
                ?.hasAnnotationsWithAllNames(
                    listOf("com.lemonappdev.konsist.testdata.fixtureannotation"),
                    ignoreCase = false,
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    listOf("com.lemonappdev.konsist.testdata.fixtureannotation"),
                    ignoreCase = true,
                ).shouldBeEqualTo(true)
            it
                ?.hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.fixtureannotation",
                        "com.lemonappdev.konsist.testdata.nonexistingannotation",
                    ),
                    ignoreCase = true,
                ).shouldBeEqualTo(false)
            it?.hasAnnotation { annotation -> annotation.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            it?.hasAnnotation { annotation -> annotation.hasNameStartingWith("Other") } shouldBeEqualTo false
            it?.hasAllAnnotations { annotation -> annotation.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(listOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(setOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(FixtureAnnotation::class) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(FixtureAnnotation::class, NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
            it?.countAnnotations { type -> type.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            it?.countAnnotations { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo 1
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationWithName("FixtureAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(setOf("FixtureAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                ?.hasAnnotationWithName(
                    setOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("FixtureAnnotation1") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                ).shouldBeEqualTo(true)

            it?.hasAnnotationsWithAllNames(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            it
                ?.hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                ?.hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                    ),
                ).shouldBeEqualTo(true)
            it?.hasAnnotation { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo true
            it?.hasAnnotation { type -> type.name == "OtherAnnotation1" } shouldBeEqualTo false
            it?.hasAllAnnotations { type -> !type.hasArguments() } shouldBeEqualTo true
            it?.hasAllAnnotations { type -> type.hasNameEndingWith("tion1") } shouldBeEqualTo false
            it?.hasAnnotationOf(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationOf(emptySet()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationOf(FixtureAnnotation1::class) shouldBeEqualTo true
            it?.hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAnnotationOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            it?.hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAnnotationOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(FixtureAnnotation1::class) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
