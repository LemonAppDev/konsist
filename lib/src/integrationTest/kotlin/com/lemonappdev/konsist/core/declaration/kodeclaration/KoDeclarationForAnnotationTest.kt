package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.reflect.KClass

class KoDeclarationForAnnotationTest {
    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithoutAnnotation")
    fun `declaration-has-no-annotation`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.annotations shouldHaveSize 0
    }

    @Test
    fun `primary-constructor-has-no-annotation`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-no-annotation")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations.isEmpty() shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-no-annotation`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-no-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.let {
            it.annotations.isEmpty() shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithOneAnnotation")
    fun `declaration-has-annotation`(
        fileName: String,
        declarationName: String,
        name: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly (sut) {
            annotations.size shouldBeEqualTo 1
            hasAnnotations(name) shouldBeEqualTo value
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithExplicitTwoAnnotations")
    fun `declaration-has-two-annotations`(
        fileName: String,
        declarationName: String,
        name1: String,
        name2: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly (sut) {
            annotations.size shouldBeEqualTo 2
            hasAnnotations(name1, name2) shouldBeEqualTo value
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithTwoAnnotations")
    fun `declaration-with-two-annotations-has-single-annotation`(
        fileName: String,
        declarationName: String,
        name: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly (sut) {
            annotations.size shouldBeEqualTo 2
            hasAnnotations(name) shouldBeEqualTo value
        }
    }

    @Test
    fun `primary-constructor-has-two-annotations`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-two-annotations")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations shouldHaveSize 2
            it.hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation1", "SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-two-annotations`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-two-annotations")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.let {
            it.annotations shouldHaveSize 2
            it.hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("SampleAnnotation1", "SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("SampleAnnotation1", "NonExistingAnnotation") shouldBeEqualTo false
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation2") shouldBeEqualTo true
            it.hasAnnotations("com.lemonappdev.konsist.testdata.NonExistingAnnotation") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithOneKClassAnnotation")
    fun `declaration-has-kclass-annotation`(
        fileName: String,
        declarationName: String,
        name: KClass<*>,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly (sut) {
            annotations.size shouldBeEqualTo 1
            hasAnnotationsOf(name) shouldBeEqualTo value
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithExplicitTwoKClassAnnotations")
    fun `declaration-has-two-kclass-annotations`(
        fileName: String,
        declarationName: String,
        name1: KClass<*>,
        name2: KClass<*>,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly (sut) {
            annotations.size shouldBeEqualTo 2
            hasAnnotationsOf(name1, name2) shouldBeEqualTo value
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithTwoKClassAnnotations")
    fun `declaration-with-two-annotations-has-single-kclass-annotation`(
        fileName: String,
        declarationName: String,
        name: KClass<*>,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly (sut) {
            annotations.size shouldBeEqualTo 2
            hasAnnotationsOf(name) shouldBeEqualTo value
        }
    }

    @Test
    fun `primary-constructor-has-two-annotations-of-kclass`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-two-annotations-of-kclass")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.let {
            it.annotations shouldHaveSize 2
            it.hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            it.hasAnnotationsOf(SampleAnnotation2::class) shouldBeEqualTo true
            it.hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            it.hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            it.hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-two-annotations-of-kclass`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-two-annotations-of-kclass")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            annotations shouldHaveSize 2
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forannotation/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithoutAnnotation() = listOf(
            arguments("class-has-no-annotation", "SampleClass"),
            arguments("function-has-no-annotation", "sampleFunction"),
            arguments("interface-has-no-annotation", "SampleInterface"),
            arguments("object-has-no-annotation", "SampleObject"),
            arguments("property-has-no-annotation", "sampleProperty"),
            arguments("typealias-has-no-annotation", "SampleTypeAlias"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithOneAnnotation() = listOf(
            arguments("class-has-annotation", "SampleClass", "SampleAnnotation", true),
            arguments("class-has-annotation", "SampleClass", "NonExistingAnnotation", false),
            arguments("class-has-annotation", "SampleClass", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("class-has-annotation", "SampleClass", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("function-has-annotation", "sampleFunction", "SampleAnnotation", true),
            arguments("function-has-annotation", "sampleFunction", "NonExistingAnnotation", false),
            arguments("function-has-annotation", "sampleFunction", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("function-has-annotation", "sampleFunction", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("interface-has-annotation", "SampleInterface", "SampleAnnotation", true),
            arguments("interface-has-annotation", "SampleInterface", "NonExistingAnnotation", false),
            arguments("interface-has-annotation", "SampleInterface", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("interface-has-annotation", "SampleInterface", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("object-has-annotation", "SampleObject", "SampleAnnotation", true),
            arguments("object-has-annotation", "SampleObject", "NonExistingAnnotation", false),
            arguments("object-has-annotation", "SampleObject", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("object-has-annotation", "SampleObject", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("property-has-annotation", "sampleProperty", "SampleAnnotation", true),
            arguments("property-has-annotation", "sampleProperty", "NonExistingAnnotation", false),
            arguments("property-has-annotation", "sampleProperty", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("property-has-annotation", "sampleProperty", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
            arguments("typealias-has-annotation", "SampleTypeAlias", "SampleAnnotation", true),
            arguments("typealias-has-annotation", "SampleTypeAlias", "NonExistingAnnotation", false),
            arguments("typealias-has-annotation", "SampleTypeAlias", "com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("typealias-has-annotation", "SampleTypeAlias", "com.lemonappdev.konsist.testdata.NonExistingAnnotation", false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithExplicitTwoAnnotations() = listOf(
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation1", "NonExistingAnnotation", false),
            arguments("typealias-has-two-annotations", "SampleTypeAlias", "SampleAnnotation1", "SampleAnnotation2", true),
            arguments("typealias-has-two-annotations", "SampleTypeAlias", "SampleAnnotation1", "NonExistingAnnotation", false),
        )
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithTwoAnnotations() = listOf(
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation1", true),
            arguments("class-has-two-annotations", "SampleClass", "SampleAnnotation2", true),
            arguments("class-has-two-annotations", "SampleClass", "NonExistingAnnotation", false),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation1", true),
            arguments("function-has-two-annotations", "sampleFunction", "SampleAnnotation2", true),
            arguments("function-has-two-annotations", "sampleFunction", "NonExistingAnnotation", false),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation1", true),
            arguments("interface-has-two-annotations", "SampleInterface", "SampleAnnotation2", true),
            arguments("interface-has-two-annotations", "SampleInterface", "NonExistingAnnotation", false),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation1", true),
            arguments("object-has-two-annotations", "SampleObject", "SampleAnnotation2", true),
            arguments("object-has-two-annotations", "SampleObject", "NonExistingAnnotation", false),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation1", true),
            arguments("property-has-two-annotations", "sampleProperty", "SampleAnnotation2", true),
            arguments("property-has-two-annotations", "sampleProperty", "NonExistingAnnotation", false),
            arguments("typealias-has-two-annotations", "SampleTypeAlias", "SampleAnnotation1", true),
            arguments("typealias-has-two-annotations", "SampleTypeAlias", "SampleAnnotation2", true),
            arguments("typealias-has-two-annotations", "SampleTypeAlias", "NonExistingAnnotation", false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithOneKClassAnnotation() = listOf(
            arguments("class-has-annotation", "SampleClass", SampleAnnotation::class, true),
            arguments("class-has-annotation", "SampleClass", NonExistingAnnotation::class, false),
            arguments("function-has-annotation", "sampleFunction", SampleAnnotation::class, true),
            arguments("function-has-annotation", "sampleFunction", NonExistingAnnotation::class, false),
            arguments("interface-has-annotation", "SampleInterface", SampleAnnotation::class, true),
            arguments("interface-has-annotation", "SampleInterface", NonExistingAnnotation::class, false),
            arguments("object-has-annotation", "SampleObject", SampleAnnotation::class, true),
            arguments("object-has-annotation", "SampleObject", NonExistingAnnotation::class, false),
            arguments("property-has-annotation", "sampleProperty", SampleAnnotation::class, true),
            arguments("property-has-annotation", "sampleProperty", NonExistingAnnotation::class, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithExplicitTwoKClassAnnotations() = listOf(
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation1::class, NonExistingAnnotation::class, false),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation1::class, SampleAnnotation2::class, true),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation1::class, NonExistingAnnotation::class, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithTwoKClassAnnotations() = listOf(
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation1::class, true),
            arguments("class-has-two-annotations", "SampleClass", SampleAnnotation2::class, true),
            arguments("class-has-two-annotations", "SampleClass", NonExistingAnnotation::class, false),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation1::class, true),
            arguments("function-has-two-annotations", "sampleFunction", SampleAnnotation2::class, true),
            arguments("function-has-two-annotations", "sampleFunction", NonExistingAnnotation::class, false),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation1::class, true),
            arguments("interface-has-two-annotations", "SampleInterface", SampleAnnotation2::class, true),
            arguments("interface-has-two-annotations", "SampleInterface", NonExistingAnnotation::class, false),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation1::class, true),
            arguments("object-has-two-annotations", "SampleObject", SampleAnnotation2::class, true),
            arguments("object-has-two-annotations", "SampleObject", NonExistingAnnotation::class, false),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation1::class, true),
            arguments("property-has-two-annotations", "sampleProperty", SampleAnnotation2::class, true),
            arguments("property-has-two-annotations", "sampleProperty", NonExistingAnnotation::class, false),
        )
    }
}
