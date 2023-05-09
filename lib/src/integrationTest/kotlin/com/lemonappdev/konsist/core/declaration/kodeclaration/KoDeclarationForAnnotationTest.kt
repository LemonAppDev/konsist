package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import sun.jvm.hotspot.oops.CellTypeState.value
import kotlin.reflect.KClass

class KoDeclarationForAnnotationTest {
    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationAnnotationSize")
    fun `annotations-size`(
        fileName: String,
        declarationName: String,
        size: Int,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.annotations shouldHaveSize size
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasAnnotation")
    fun `declaration-has-annotation`(
        fileName: String,
        declarationName: String,
        name: String,
        value: Boolean
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasAnnotations(name) shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasTwoAnnotations")
    fun `declaration-has-two-annotations`(
        fileName: String,
        declarationName: String,
        name1: String,
        name2: String,
        value: Boolean
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasAnnotations(name1, name2) shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasAnnotationKClass")
    fun `declaration-has-annotation-kclass`(
        fileName: String,
        declarationName: String,
        name: KClass<*>,
        value: Boolean
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasAnnotationsOf(name) shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasTwoAnnotationsKClass")
    fun `declaration-has-two-annotations-kclass`(
        fileName: String,
        declarationName: String,
        name1: KClass<*>,
        name2: KClass<*>,
        value: Boolean
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasAnnotationsOf(name1, name2) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forannotation/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationAnnotationSize() = listOf(
            arguments("class-has-no-annotation", "SampleClass", 0),
            arguments("class-has-annotation", "SampleClass", 1),
            arguments("class-has-two-annotations", "SampleClass", 2),
            arguments("companion-object-has-no-annotation", "SampleCompanionObject", 0),
            arguments("companion-object-has-annotation", "SampleCompanionObject", 1),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", 2),
            arguments("function-has-no-annotation", "sampleFunction", 0),
            arguments("function-has-annotation", "sampleFunction", 1),
            arguments("function-has-two-annotations", "sampleFunction", 2),
            arguments("interface-has-no-annotation", "SampleInterface", 0),
            arguments("interface-has-annotation", "SampleInterface", 1),
            arguments("interface-has-two-annotations", "SampleInterface", 2),
            arguments("object-has-no-annotation", "SampleObject", 0),
            arguments("object-has-annotation", "SampleObject", 1),
            arguments("object-has-two-annotations", "SampleObject", 2),
            arguments("property-has-no-annotation", "sampleProperty", 0),
            arguments("property-has-annotation", "sampleProperty", 1),
            arguments("property-has-two-annotations", "sampleProperty", 2),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasAnnotation() = listOf(
            arguments("class-has-annotation", "SampleClass", "SampleAnnotation", true),
            arguments("class-has-annotation", "SampleClass", "NonExistingAnnotation", false),
            arguments("class-has-annotation", "SampleClass", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("class-has-annotation", "SampleClass", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation1", true),
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation2", true),
            arguments("class-has-two-annotations", "SampleClass", "NonExistingAnnotation", false),
            arguments("companion-object-has-annotation", "SampleCompanionObject", "SampleAnnotation", true),
            arguments("companion-object-has-annotation", "SampleCompanionObject", "NonExistingAnnotation", false),
            arguments("companion-object-has-annotation", "SampleCompanionObject", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("companion-object-has-annotation", "SampleCompanionObject", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", "SampleAnnotation1", true),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", "SampleAnnotation2", true),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", "NonExistingAnnotation", false),
            arguments("function-has-annotation", "sampleFunction", "SampleAnnotation", true),
            arguments("function-has-annotation", "sampleFunction", "NonExistingAnnotation", false),
            arguments("function-has-annotation", "sampleFunction", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("function-has-annotation", "sampleFunction", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation1", true),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation2", true),
            arguments("function-has-two-annotations", "sampleFunction", "NonExistingAnnotation", false),
            arguments("interface-has-annotation", "SampleInterface", "SampleAnnotation", true),
            arguments("interface-has-annotation", "SampleInterface", "NonExistingAnnotation", false),
            arguments("interface-has-annotation", "SampleInterface", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("interface-has-annotation", "SampleInterface", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation1", true),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation2", true),
            arguments("interface-has-two-annotations", "SampleInterface", "NonExistingAnnotation", false),
            arguments("object-has-annotation", "SampleObject", "SampleAnnotation", true),
            arguments("object-has-annotation", "SampleObject", "NonExistingAnnotation", false),
            arguments("object-has-annotation", "SampleObject", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("object-has-annotation", "SampleObject", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation1", true),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation2", true),
            arguments("object-has-two-annotations", "SampleObject", "NonExistingAnnotation", false),
            arguments("property-has-annotation", "sampleProperty", "SampleAnnotation", true),
            arguments("property-has-annotation", "sampleProperty", "NonExistingAnnotation", false),
            arguments("property-has-annotation", "sampleProperty", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("property-has-annotation", "sampleProperty", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation1", true),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation2", true),
            arguments("property-has-two-annotations", "sampleProperty", "NonExistingAnnotation", false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasTwoAnnotations() = listOf(
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation1", "NonExistingAnnotation", false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasAnnotationKClass() = listOf(
            arguments("class-has-annotation", "SampleClass", SampleAnnotation::class, true),
            arguments("class-has-annotation", "SampleClass", NonExistingAnnotation::class, false),
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation1::class, true),
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation2::class, true),
            arguments("class-has-two-annotations", "SampleClass", NonExistingAnnotation::class, false),
            arguments("companion-object-has-annotation", "SampleCompanionObject", SampleAnnotation::class, true),
            arguments("companion-object-has-annotation", "SampleCompanionObject", NonExistingAnnotation::class, false),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", SampleAnnotation1::class, true),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", SampleAnnotation2::class, true),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", NonExistingAnnotation::class, false),
            arguments("function-has-annotation", "sampleFunction", SampleAnnotation::class, true),
            arguments("function-has-annotation", "sampleFunction", NonExistingAnnotation::class, false),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation1::class, true),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation2::class, true),
            arguments("function-has-two-annotations", "sampleFunction", NonExistingAnnotation::class, false),
            arguments("interface-has-annotation", "SampleInterface", SampleAnnotation::class, true),
            arguments("interface-has-annotation", "SampleInterface", NonExistingAnnotation::class, false),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation1::class, true),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation2::class, true),
            arguments("interface-has-two-annotations", "SampleInterface", NonExistingAnnotation::class, false),
            arguments("object-has-annotation", "SampleObject", SampleAnnotation::class, true),
            arguments("object-has-annotation", "SampleObject", NonExistingAnnotation::class, false),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation1::class, true),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation2::class, true),
            arguments("object-has-two-annotations", "SampleObject", NonExistingAnnotation::class, false),
            arguments("property-has-annotation", "sampleProperty", SampleAnnotation::class, true),
            arguments("property-has-annotation", "sampleProperty", NonExistingAnnotation::class, false),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation1::class, true),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation2::class, true),
            arguments("property-has-two-annotations", "sampleProperty", NonExistingAnnotation::class, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasTwoAnnotationsKClass() = listOf(
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("companion-object-has-two-annotations", "SampleCompanionObject", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation1::class, NonExistingAnnotation::class, false),
        )
    }
}
