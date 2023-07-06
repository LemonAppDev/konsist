package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoNamedDeclarationForNameMatchingTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-has-name-with-prefix`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .baseDeclarations()
            .first { it.name == declarationName }

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornamematching/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-name-matching", "SampleClass", "Sam"),
            arguments("function-has-name-matching", "sampleFunction", "sam"),
            arguments("interface-has-name-matching", "SampleInterface", "Sam"),
            arguments("object-has-name-matching", "SampleObject", "Sam"),
            arguments("property-has-name-matching", "sampleProperty", "sam"),
        )
    }
}
