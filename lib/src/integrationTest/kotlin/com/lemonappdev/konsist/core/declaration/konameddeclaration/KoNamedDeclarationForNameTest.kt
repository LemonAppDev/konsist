package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoNamedDeclarationForNameTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-name`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .baseDeclarations()
            .first()

        // then
        sut.name shouldBeEqualTo declarationName
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/forname/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class", "SampleClass"),
            arguments("function", "sampleFunction"),
            arguments("interface", "SampleInterface"),
            arguments("object", "SampleObject"),
            arguments("property", "sampleProperty"),
        )
    }
}
