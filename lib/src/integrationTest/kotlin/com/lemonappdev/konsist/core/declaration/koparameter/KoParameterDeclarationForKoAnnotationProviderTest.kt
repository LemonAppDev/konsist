package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.FixtureAnnotation
import com.lemonappdev.konsist.testdata.FixtureAnnotation1
import com.lemonappdev.konsist.testdata.FixtureAnnotation2
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoParameterDeclarationForKoAnnotationProviderTest {
    @Test
    fun `parameter-in-constructor-has-no-annotation`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-has-no-annotation")
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

    @Test
    fun `parameter-in-function-invocation-has-no-annotation`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-has-no-annotation")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            annotations shouldBeEqualTo emptyList()
            numAnnotations shouldBeEqualTo 0
            countAnnotations { annotation -> annotation.name == "NonExistingAnnotation" } shouldBeEqualTo 0
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
            hasAnnotation { annotation -> annotation.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { annotation -> annotation.hasArguments() } shouldBeEqualTo true
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

    @Test
    fun `parameter-in-constructor-has-annotation`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-has-annotation")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

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

    @Test
    fun `parameter-in-function-invocation-has-annotation`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-has-annotation")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            countAnnotations { annotation -> annotation.name == "FixtureAnnotation" } shouldBeEqualTo 1
            countAnnotations { annotation -> annotation.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationWithName(emptyList()) shouldBeEqualTo true
            hasAnnotationWithName(emptySet()) shouldBeEqualTo true
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo true
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo true
            hasAnnotationWithName("FixtureAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", ignoreCase = true) shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("otherannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName("otherannotation", ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName("FixtureAnnotation", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("fixtureannotation", "otherannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName("fixtureannotation", "otherannotation", ignoreCase = true) shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.FixtureAnnotation") shouldBeEqualTo true
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                "com.lemonappdev.konsist.testdata.OtherAnnotation",
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                "com.lemonappdev.konsist.testdata.otherannotation",
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("FixtureAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("otherannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationWithName(listOf("otherannotation"), ignoreCase = true) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(
                listOf("fixtureannotation", "otherannotation"),
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(listOf("fixtureannotation", "otherannotation"), ignoreCase = true) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                ),
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.otherannotation",
                ),
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.otherannotation",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                    "com.lemonappdev.konsist.testdata.OtherAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.otherannotation",
                ),
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.otherannotation",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("FixtureAnnotation") shouldBeEqualTo true
            hasAnnotationsWithAllNames("fixtureannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationsWithAllNames("fixtureannotation", ignoreCase = true) shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("fixtureannotation", "otherannotation", ignoreCase = false) shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation") shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.fixtureannotation",
                "com.lemonappdev.konsist.testdata.nonexistingannotation",
                ignoreCase = true,
            ).shouldBeEqualTo(false)

            hasAnnotationsWithAllNames(listOf("FixtureAnnotation")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("fixtureannotation"), ignoreCase = false) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("fixtureannotation"), ignoreCase = true) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(
                listOf("fixtureannotation", "otherannotation"),
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                listOf("com.lemonappdev.konsist.testdata.fixtureannotation"),
                ignoreCase = false,
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                listOf("com.lemonappdev.konsist.testdata.fixtureannotation"),
                ignoreCase = true,
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.fixtureannotation",
                    "com.lemonappdev.konsist.testdata.nonexistingannotation",
                ),
                ignoreCase = true,
            ).shouldBeEqualTo(false)
            hasAnnotation { annotation -> annotation.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAnnotation { annotation -> annotation.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllAnnotations { annotation -> annotation.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(listOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(FixtureAnnotation::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(FixtureAnnotation::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-constructor-has-two-annotations`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-has-two-annotations")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 2
            it?.countAnnotations { annotation -> annotation.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            it?.countAnnotations { annotation -> annotation.name == "FixtureAnnotation1" } shouldBeEqualTo 1
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
            it?.hasAnnotation { annotation -> annotation.name == "FixtureAnnotation1" } shouldBeEqualTo true
            it?.hasAnnotation { annotation -> annotation.name == "OtherAnnotation1" } shouldBeEqualTo false
            it?.hasAllAnnotations { annotation -> !annotation.hasArguments() } shouldBeEqualTo true
            it?.hasAllAnnotations { annotation -> annotation.hasNameEndingWith("tion1") } shouldBeEqualTo false
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
            it?.hasAllAnnotationsOf(
                listOf(
                    FixtureAnnotation1::class,
                    NonExistingAnnotation::class,
                ),
            ) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-function-invocation-has-two-annotations`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-has-two-annotations")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            countAnnotations { annotation -> annotation.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            countAnnotations { annotation -> annotation.name == "FixtureAnnotation1" } shouldBeEqualTo 1
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
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(
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
            hasAnnotationWithName(
                setOf(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "FixtureAnnotation2") shouldBeEqualTo true
            hasAnnotationsWithAllNames("FixtureAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.FixtureAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
            ).shouldBeEqualTo(true)

            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "FixtureAnnotation2")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("FixtureAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.FixtureAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation1",
                    "com.lemonappdev.konsist.testdata.FixtureAnnotation2",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotation { annotation -> annotation.name == "FixtureAnnotation1" } shouldBeEqualTo true
            hasAnnotation { annotation -> annotation.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { annotation -> !annotation.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { annotation -> annotation.hasNameEndingWith("tion1") } shouldBeEqualTo false
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
            hasAllAnnotationsOf(
                listOf(
                    FixtureAnnotation1::class,
                    NonExistingAnnotation::class,
                ),
            ) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, FixtureAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(FixtureAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-constructor-has-suppress-annotation-without-import`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-has-suppress-annotation-without-import")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.numAnnotations shouldBeEqualTo 1
            it?.hasAllAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `parameter-in-function-invocation-has-suppress-annotation-without-import`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-has-suppress-annotation-without-import")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            hasAllAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkoannotationprovider/", fileName)
}
