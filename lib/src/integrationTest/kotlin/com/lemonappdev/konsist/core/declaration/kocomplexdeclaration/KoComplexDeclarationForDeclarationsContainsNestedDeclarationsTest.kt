package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationForDeclarationsContainsNestedDeclarationsTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-nested-declarations includeNested true includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = false)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleLocalClass",
            "sampleLocalFunction",
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
    @MethodSource("provideValues")
    fun `declaration-contains-nested-declarations includeNested true includeLocal false`(
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

        sut.declarations(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-nested-declarations includeNested false includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = false)
            .filterIsInstance<KoComplexDeclaration>()
            .first { it.name == declarationName }

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut.declarations(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-nested-declarations includeNested false includeLocal false`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = false)
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
            .declarations(includeNested = false, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/fordeclarationscontainsnesteddeclarations/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-contains-nested-declarations", "SampleTopLevelClass"),
            arguments("interface-contains-nested-declarations", "SampleTopLevelInterface"),
            arguments("object-contains-nested-declarations", "SampleTopLevelObject"),
        )
    }
}
