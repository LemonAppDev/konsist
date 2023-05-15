package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationForDeclarationsTest {
    @ParameterizedTest
    @MethodSource("provideValuesForNoDeclarations")
    fun `declaration-contains-no-declarations`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(emptyList())
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarations")
    fun `declaration-contains-declarations includeNested true includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarations")
    fun `declaration-contains-declarations includeNested true includeLocal false`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarations")
    fun `declaration-contains-declarations includeNested false includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForNestedDeclarations")
    fun `declaration-contains-nested-declarations includeNested true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
            "SampleObjectNestedInsideClass",
            "SampleInterfaceNestedInsideClass",
            "SampleObject",
            "SampleClassNestedInsideObject",
            "SampleObjectNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleInterface",
            "SampleClassNestedInsideInterface",
            "SampleObjectNestedInsideInterface",
            "SampleInterfaceNestedInsideInterface",
        )

        sut.declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForNestedDeclarations")
    fun `declaration-contains-nested-declarations includeNested false`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForLocalDeclarations")
    fun `declaration-contains-local-declarations includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
            "sampleLocalProperty2",
            "sampleLocalFunction2",
            "SampleLocalClass1",
            "sampleLocalFunction2",
        )

        sut
            .declarations(includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForLocalDeclarations")
    fun `declaration-contains-local-declarations includeLocal false`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/fordeclarations/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoDeclarations() = listOf(
            arguments("class-contains-no-declarations", "SampleClass"),
            arguments("interface-contains-no-declarations", "SampleInterface"),
            arguments("object-contains-no-declarations", "SampleObject"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarations() = listOf(
            arguments("class-contains-declarations", "SampleTopLevelClass"),
            arguments("interface-contains-declarations", "SampleTopLevelInterface"),
            arguments("object-contains-declarations", "SampleTopLevelObject"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNestedDeclarations() = listOf(
            arguments("class-contains-nested-declarations", "SampleTopLevelClass"),
            arguments("interface-contains-nested-declarations", "SampleTopLevelInterface"),
            arguments("object-contains-nested-declarations", "SampleTopLevelObject"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForLocalDeclarations() = listOf(
            arguments("class-contains-local-declarations", "SampleTopLevelClass"),
            arguments("interface-contains-local-declarations", "SampleTopLevelInterface"),
            arguments("object-contains-local-declarations", "SampleTopLevelObject"),
        )
    }
}
