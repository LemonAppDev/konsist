package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoNamedDeclarationForNameEndingWithTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-has-name-with-suffix`(
        fileName: String,
        declarationName: String,
        suffix: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations()
            .first { it.name == declarationName }

        // then
        assertSoftly(sut) {
            hasNameEndingWith(suffix) shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornameendingwith/".toNormalizedPath(), fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-name-with-suffix", "SampleClass", "lass"),
            arguments("function-has-name-with-suffix", "sampleFunction", "ion"),
            arguments("interface-has-name-with-suffix", "SampleInterface", "ace"),
            arguments("object-has-name-with-suffix", "SampleObject", "ect"),
            arguments("property-has-name-with-suffix", "sampleProperty", "rty"),
        )
    }
}
