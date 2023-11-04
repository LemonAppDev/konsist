package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoKotlinTypeProviderTest {
    @ParameterizedTest(name = "{0} is-kotlin-type {1}")
    @MethodSource("provideValues")
    fun `is-kotlin-type`(
        fileName: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.isKotlinType shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/forkokotlintypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("string", true),
            arguments("list-of-string", true),
            arguments("list-of-sample-type", true),
            arguments("sample-collection-of-string", false),
            arguments("sample-collection-of-sample-type", false),
            arguments("sample-collection-with-two-type-arguments-and-one-is-kotlin-type", false),
            arguments("sample-collection-with-two-non-kotlin-type-arguments", false),
            arguments("sample-collection-with-in-keyword-and-kotlin-type", false),
            arguments("sample-collection-with-in-keyword-and-non-kotlin-type", false),
        )
    }
}
