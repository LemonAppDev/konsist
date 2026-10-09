package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.testdata.FixtureAnnotation
import com.lemonappdev.konsist.testdata.FixtureAnnotation1
import com.lemonappdev.konsist.testdata.FixtureAnnotation2
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("detekt.LongMethod")
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
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(setOf("FixtureAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(setOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            hasAnnotation { it.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { it.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo false
            hasAnnotationOf(emptySet()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
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
            countAnnotations { type -> type.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            countAnnotations { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo 1
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationWithName("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(setOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    setOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                ).shouldBeEqualTo(true)

            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotation { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo true
            hasAnnotation { type -> type.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { type -> !type.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { type -> type.hasNameEndingWith("tion1") } shouldBeEqualTo false
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationOf(FixtureAnnotation1::class) shouldBeEqualTo true
            hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            hasAnnotationOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation1::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(setOf("FixtureAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(setOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            hasAnnotation { it.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { it.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo false
            hasAnnotationOf(emptySet()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForTwoAnnotations")
    fun `interface-parent-has-two-annotations`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            countAnnotations { type -> type.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            countAnnotations { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo 1
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationWithName("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(setOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    setOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                ).shouldBeEqualTo(true)

            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotation { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo true
            hasAnnotation { type -> type.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { type -> !type.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { type -> type.hasNameEndingWith("tion1") } shouldBeEqualTo false
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationOf(FixtureAnnotation1::class) shouldBeEqualTo true
            hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            hasAnnotationOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation1::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(setOf("FixtureAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(setOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(setOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo false
            hasAnnotation { it.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { it.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo false
            hasAnnotationOf(emptySet()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo false
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForTwoAnnotations")
    fun `object-parent-has-two-annotations`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            countAnnotations { type -> type.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            countAnnotations { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo 1
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationWithName("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationWithName(setOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it
                .hasAnnotationWithName(
                    setOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                ).shouldBeEqualTo(true)

            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                    ),
                ).shouldBeEqualTo(false)
            it
                .hasAnnotationsWithAllNames(
                    listOf(
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                        "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                    ),
                ).shouldBeEqualTo(true)
            hasAnnotation { type -> type.name == "FixtureAnnotation1" } shouldBeEqualTo true
            hasAnnotation { type -> type.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { type -> !type.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { type -> type.hasNameEndingWith("tion1") } shouldBeEqualTo false
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationOf(FixtureAnnotation1::class) shouldBeEqualTo true
            hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            hasAnnotationOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation1::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation1::class, FixtureAnnotation2::class) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
                arguments("class-with-typealias-parent-without-annotation"),
                arguments("class-with-import-alias-parent-without-annotation"),
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
                arguments("class-with-typealias-parent-with-two-annotations"),
                arguments("class-with-import-alias-parent-with-two-annotations"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoAnnotation() =
            listOf(
                arguments("interface-with-parent-interface-without-annotation"),
                arguments("interface-with-generic-parent-interface-without-annotation"),
                arguments("interface-with-external-parent-interface-without-annotation"),
                arguments("interface-with-generic-external-parent-interface-without-annotation"),
                arguments("interface-with-typealias-parent-without-annotation"),
                arguments("interface-with-import-alias-parent-without-annotation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForTwoAnnotations() =
            listOf(
                arguments("interface-with-parent-interface-with-two-annotations"),
                arguments("interface-with-generic-parent-interface-with-two-annotations"),
                arguments("interface-with-external-parent-interface-with-two-annotations"),
                arguments("interface-with-generic-external-parent-interface-with-two-annotations"),
                arguments("interface-with-typealias-parent-with-two-annotations"),
                arguments("interface-with-import-alias-parent-with-two-annotations"),
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
                arguments("object-with-typealias-parent-without-annotation"),
                arguments("object-with-import-alias-parent-without-annotation"),
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
                arguments("object-with-typealias-parent-with-two-annotations"),
                arguments("object-with-import-alias-parent-with-two-annotations"),
            )
    }
}
