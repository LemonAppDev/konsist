package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
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
            it?.countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo false
            it?.hasAnnotationWithName(emptyList()) shouldBeEqualTo false
            it?.hasAnnotationWithName(emptySet()) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(setOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo false
            it?.hasAnnotation { it.hasArguments() } shouldBeEqualTo false
            it?.hasAllAnnotations { it.hasArguments() } shouldBeEqualTo true
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
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
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
            hasAnnotation { it.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { it.hasArguments() } shouldBeEqualTo true
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
            it?.countAnnotations { it.name == "SampleAnnotation" } shouldBeEqualTo 1
            it?.countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            it?.hasAnnotations() shouldBeEqualTo true
            it?.hasAnnotationWithName(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationWithName(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo true
            it?.hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(setOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(
                setOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(true)
            it?.hasAnnotationsWithAllNames("SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(false)
            it?.hasAnnotation { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            it?.hasAnnotation { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            it?.hasAllAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
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
            countAnnotations { it.name == "SampleAnnotation" } shouldBeEqualTo 1
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationWithName(emptyList()) shouldBeEqualTo true
            hasAnnotationWithName(emptySet()) shouldBeEqualTo true
            hasAnnotationsWithAllNames(emptyList()) shouldBeEqualTo true
            hasAnnotationsWithAllNames(emptySet()) shouldBeEqualTo true
            hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("SampleAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(setOf("SampleAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(setOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(setOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(
                setOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("SampleAnnotation") shouldBeEqualTo true
            hasAnnotationsWithAllNames("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(listOf("SampleAnnotation")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("SampleAnnotation", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(false)
            hasAnnotation { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAnnotation { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAnnotationOf(emptyList()) shouldBeEqualTo true
            hasAnnotationOf(emptySet()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptyList()) shouldBeEqualTo true
            hasAllAnnotationsOf(emptySet()) shouldBeEqualTo true
            hasAnnotationOf(listOf(SampleAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(listOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(SampleAnnotation::class)) shouldBeEqualTo true
            hasAnnotationOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAnnotationOf(setOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(listOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(listOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
            it?.countAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            it?.countAnnotations { it.name == "SampleAnnotation1" } shouldBeEqualTo 1
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
            it?.hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            it?.hasAnnotationWithName(listOf("SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationWithName(
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
            it?.hasAnnotationWithName(
                setOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
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

            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            it?.hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            it?.hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(false)
            it?.hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                ),
            ).shouldBeEqualTo(true)
            it?.hasAnnotation { it.name == "SampleAnnotation1" } shouldBeEqualTo true
            it?.hasAnnotation { it.name == "OtherAnnotation1" } shouldBeEqualTo false
            it?.hasAllAnnotations { !it.hasArguments() } shouldBeEqualTo true
            it?.hasAllAnnotations { it.hasNameEndingWith("tion1") } shouldBeEqualTo false
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
            it?.hasAllAnnotationsOf(
                listOf(
                    SampleAnnotation1::class,
                    NonExistingAnnotation::class
                )
            ) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo true
            it?.hasAllAnnotationsOf(setOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
            countAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            countAnnotations { it.name == "SampleAnnotation1" } shouldBeEqualTo 1
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
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            hasAnnotationWithName(listOf("SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationWithName(listOf("com.lemonappdev.konsist.testdata.NonExistingAnnotation")) shouldBeEqualTo false
            hasAnnotationWithName(
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
            hasAnnotationWithName(
                setOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            hasAnnotationsWithAllNames("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.SampleAnnotation2",
            ).shouldBeEqualTo(true)

            hasAnnotationsWithAllNames(listOf("SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "SampleAnnotation2")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(listOf("SampleAnnotation1", "OtherAnnotation")) shouldBeEqualTo false
            hasAnnotationsWithAllNames(listOf("com.lemonappdev.konsist.testdata.SampleAnnotation1")) shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
                ),
            ).shouldBeEqualTo(false)
            hasAnnotationsWithAllNames(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                ),
            ).shouldBeEqualTo(true)
            hasAnnotation { it.name == "SampleAnnotation1" } shouldBeEqualTo true
            hasAnnotation { it.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { !it.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { it.hasNameEndingWith("tion1") } shouldBeEqualTo false
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
            hasAllAnnotationsOf(
                listOf(
                    SampleAnnotation1::class,
                    NonExistingAnnotation::class
                )
            ) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(NonExistingAnnotation::class)) shouldBeEqualTo false
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, SampleAnnotation2::class)) shouldBeEqualTo true
            hasAllAnnotationsOf(setOf(SampleAnnotation1::class, NonExistingAnnotation::class)) shouldBeEqualTo false
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
