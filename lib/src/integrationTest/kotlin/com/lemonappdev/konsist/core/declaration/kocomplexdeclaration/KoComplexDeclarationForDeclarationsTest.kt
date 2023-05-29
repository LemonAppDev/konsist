package com.lemonappdev.konsist.core.declaration.kocomplexdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.core.ext.toNormalizedPath
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
    fun `declaration-contains-declarations`(
        fileName: String,
        declarationName: String,
        includeNested: Boolean,
        includeLocal: Boolean,
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
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocomplexdeclaration/snippet/fordeclarations/".toNormalizedPath(), fileName)

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
            arguments("class-contains-declarations", "SampleTopLevelClass", true, true),
            arguments("class-contains-declarations", "SampleTopLevelClass", true, false),
            arguments("class-contains-declarations", "SampleTopLevelClass", false, true),
            arguments("class-contains-declarations", "SampleTopLevelClass", false, false),
            arguments("interface-contains-declarations", "SampleTopLevelInterface", true, true),
            arguments("interface-contains-declarations", "SampleTopLevelInterface", true, false),
            arguments("interface-contains-declarations", "SampleTopLevelInterface", false, true),
            arguments("interface-contains-declarations", "SampleTopLevelInterface", false, false),
            arguments("object-contains-declarations", "SampleTopLevelObject", true, true),
            arguments("object-contains-declarations", "SampleTopLevelObject", true, false),
            arguments("object-contains-declarations", "SampleTopLevelObject", false, true),
            arguments("object-contains-declarations", "SampleTopLevelObject", false, false),
        )
    }
}
