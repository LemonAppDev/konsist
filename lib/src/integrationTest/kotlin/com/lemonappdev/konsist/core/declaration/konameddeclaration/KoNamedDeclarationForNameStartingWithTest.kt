package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoNamedDeclarationForNameStartingWithTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-has-name-with-prefix`(
        fileName: String,
        declarationName: String,
        prefix: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations()
            .first { it.name == declarationName }

        // then
        assertSoftly(sut) {
            hasNameStartingWith(prefix) shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/konameddeclaration/snippet/fornamestartingwith/".toNormalizedPath(),
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-name-with-prefix", "SampleClass", "Sam"),
            arguments("function-has-name-with-prefix", "sampleFunction", "sam"),
            arguments("interface-has-name-with-prefix", "SampleInterface", "Sam"),
            arguments("object-has-name-with-prefix", "SampleObject", "Sam"),
            arguments("property-has-name-with-prefix", "sampleProperty", "sam"),
        )
    }
}
