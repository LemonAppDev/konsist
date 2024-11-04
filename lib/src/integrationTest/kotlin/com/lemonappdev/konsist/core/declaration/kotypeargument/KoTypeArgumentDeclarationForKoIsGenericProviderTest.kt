package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeArgumentDeclarationForKoIsGenericProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-argument-is-not-generic`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isGeneric shouldBeEqualTo false
    }

    @Test
    fun `generic-type-argument`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isGeneric shouldBeEqualTo true
    }

    @Test
    fun `typealias-with-generic-type-argument`() {
        // given
        val sut =
            getSnippetFile("typealias-with-generic-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isGeneric shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkoisgenericprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-type-argument"),
                arguments("external-type-argument"),
                arguments("function-type-argument"),
                arguments("import-alias-type-argument"),
                arguments("interface-type-argument"),
                arguments("kotlin-type-argument"),
                arguments("object-type-argument"),
                arguments("typealias-without-generic-type-argument"),
            )
    }
}
