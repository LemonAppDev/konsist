package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
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
class KoVariableDeclarationForKoAnnotationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoAnnotation")
    fun `variable-has-no-annotation`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
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
    @MethodSource("provideValuesForOneAnnotation")
    fun `variable-has-annotation`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            countAnnotations { it.name == "FixtureAnnotation" } shouldBeEqualTo 1
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
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
            hasAnnotation { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAnnotation { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllAnnotations { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
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

    @ParameterizedTest
    @MethodSource("provideValuesForTwoAnnotations")
    fun `variable-has-two-annotations`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            countAnnotations { it.hasNameStartingWith("Fixture") } shouldBeEqualTo 2
            countAnnotations { it.name == "FixtureAnnotation1" } shouldBeEqualTo 1
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
            hasAnnotation { it.name == "FixtureAnnotation1" } shouldBeEqualTo true
            hasAnnotation { it.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { !it.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { it.hasNameEndingWith("tion1") } shouldBeEqualTo false
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
    @MethodSource("provideValuesForSuppressAnnotation")
    fun `variable-has-suppress-annotation-without-import`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            hasAllAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkoannotationprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoAnnotation() =
            listOf(
                arguments(getSnippetFile("variable-in-function-has-no-annotation").functions()),
                arguments(getSnippetFile("variable-in-init-block-has-no-annotation").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-has-no-annotation").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-has-no-annotation").properties().getters),
                arguments(getSnippetFile("variable-in-setter-has-no-annotation").properties().setters),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForOneAnnotation() =
            listOf(
                arguments(getSnippetFile("variable-in-function-has-annotation").functions()),
                arguments(getSnippetFile("variable-in-init-block-has-annotation").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-has-annotation").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-has-annotation").properties().getters),
                arguments(getSnippetFile("variable-in-setter-has-annotation").properties().setters),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTwoAnnotations() =
            listOf(
                arguments(getSnippetFile("variable-in-function-has-two-annotations").functions()),
                arguments(getSnippetFile("variable-in-init-block-has-two-annotations").classes().initBlocks),
                arguments(getSnippetFile("variable-in-enum-constant-has-two-annotations").classes().enumConstants),
                arguments(getSnippetFile("variable-in-getter-has-two-annotations").properties().getters),
                arguments(getSnippetFile("variable-in-setter-has-two-annotations").properties().setters),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSuppressAnnotation() =
            listOf(
                arguments(
                    getSnippetFile("variable-in-function-has-suppress-annotation-without-import").functions(),
                ),
                arguments(
                    getSnippetFile("variable-in-init-block-has-suppress-annotation-without-import").classes().initBlocks,
                ),
                arguments(
                    getSnippetFile("variable-in-enum-constant-has-suppress-annotation-without-import").classes().enumConstants,
                ),
                arguments(
                    getSnippetFile("variable-in-getter-has-suppress-annotation-without-import").properties().getters,
                ),
                arguments(
                    getSnippetFile("variable-in-setter-has-suppress-annotation-without-import").properties().setters,
                ),
            )
    }
}
