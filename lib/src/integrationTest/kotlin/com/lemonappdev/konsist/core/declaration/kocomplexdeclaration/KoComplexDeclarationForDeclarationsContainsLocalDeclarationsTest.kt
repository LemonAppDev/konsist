package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoComplexDeclarationForDeclarationsContainsLocalDeclarationsTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-local-declarations includeNested true includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName } as KoDeclarationProvider

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
            "sampleLocalProperty2",
            "sampleLocalFunction2",
            "SampleLocalClass1",
            "sampleLocalFunction3",
        )

        sut
            .declarations(includeNested = true, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-local-declarations includeNested true includeLocal false`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName } as KoDeclarationProvider

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeNested = true, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-local-declarations includeNested false includeLocal true`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName } as KoDeclarationProvider

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
            "sampleLocalProperty2",
            "sampleLocalFunction2",
            "SampleLocalClass1",
        )

        sut
            .declarations(includeNested = false, includeLocal = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-contains-local-declarations includeNested false includeLocal false`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName } as KoDeclarationProvider

        // then
        val expected = listOf("sampleFunction")

        sut
            .declarations(includeNested = false, includeLocal = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kocomplexdeclaration/snippet/fordeclarationscontainslocaldeclarations/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-contains-local-declarations", "SampleTopLevelClass"),
            arguments("interface-contains-local-declarations", "SampleTopLevelInterface"),
            arguments("object-contains-local-declarations", "SampleTopLevelObject"),
        )
    }
}
