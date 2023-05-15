package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoNamedDeclarationForNameContainingTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-has-name-containing`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations()
            .first { it.name == declarationName }

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornamecontaining/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-name-containing", "SampleClass"),
            arguments("function-has-name-containing", "sampleFunction"),
            arguments("interface-has-name-containing", "SampleInterface"),
            arguments("object-has-name-containing", "SampleObject"),
            arguments("property-has-name-containing", "sampleProperty"),
        )
    }
}
