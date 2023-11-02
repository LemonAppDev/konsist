package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoAnnotationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoAnnotation")
    fun `variable-has-no-annotation`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            annotations shouldBeEqualTo emptyList()
            numAnnotations shouldBeEqualTo 0
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo false
            hasAnnotation { it.hasArguments() } shouldBeEqualTo false
            hasAllAnnotations { it.hasArguments() } shouldBeEqualTo true
            hasAnnotationOf(SampleAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo false
            hasAnnotations("SampleAnnotation") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForOneAnnotation")
    fun `variable-has-annotation`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            countAnnotations { it.name == "SampleAnnotation" } shouldBeEqualTo 1
            countAnnotations { it.name == "NonExistingAnnotation" } shouldBeEqualTo 0
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationWithName("SampleAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(true)
            hasAnnotationsWithAllNames("SampleAnnotation") shouldBeEqualTo true
            hasAnnotationsWithAllNames("SampleAnnotation", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotationsWithAllNames("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotationsWithAllNames(
                "com.lemonappdev.konsist.testdata.SampleAnnotation",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
            ).shouldBeEqualTo(false)
            hasAnnotation { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAnnotation { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAnnotationOf(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation::class, NonExistingAnnotation::class) shouldBeEqualTo true
            hasAllAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotations("SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTwoAnnotations")
    fun `variable-has-two-annotations`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 2
            countAnnotations { it.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            countAnnotations { it.name == "SampleAnnotation1" } shouldBeEqualTo 1
            hasAnnotations() shouldBeEqualTo true
            hasAnnotationWithName("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("OtherAnnotation") shouldBeEqualTo false
            hasAnnotationWithName("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotationWithName("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationWithName(
                "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                "com.lemonappdev.konsist.testdata.NonExistingAnnotation",
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
            hasAnnotation { it.name == "SampleAnnotation1" } shouldBeEqualTo true
            hasAnnotation { it.name == "OtherAnnotation1" } shouldBeEqualTo false
            hasAllAnnotations { !it.hasArguments() } shouldBeEqualTo true
            hasAllAnnotations { it.hasNameEndingWith("tion1") } shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo true
            hasAllAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAllAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAllAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            hasAllAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotations("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForSuppressAnnotation")
    fun `variable-has-suppress-annotation-without-import`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            numAnnotations shouldBeEqualTo 1
            hasAnnotationsOf(Suppress::class) shouldBeEqualTo true
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkoannotationprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoAnnotation() = listOf(
            arguments(getSnippetFile("variable-in-function-has-no-annotation").functions()),
            arguments(getSnippetFile("variable-in-init-block-has-no-annotation").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-has-no-annotation").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-has-no-annotation").properties().getters),
            arguments(getSnippetFile("variable-in-setter-has-no-annotation").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForOneAnnotation() = listOf(
            arguments(getSnippetFile("variable-in-function-has-annotation").functions()),
            arguments(getSnippetFile("variable-in-init-block-has-annotation").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-has-annotation").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-has-annotation").properties().getters),
            arguments(getSnippetFile("variable-in-setter-has-annotation").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTwoAnnotations() = listOf(
            arguments(getSnippetFile("variable-in-function-has-two-annotations").functions()),
            arguments(getSnippetFile("variable-in-init-block-has-two-annotations").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-has-two-annotations").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-has-two-annotations").properties().getters),
            arguments(getSnippetFile("variable-in-setter-has-two-annotations").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSuppressAnnotation() = listOf(
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
