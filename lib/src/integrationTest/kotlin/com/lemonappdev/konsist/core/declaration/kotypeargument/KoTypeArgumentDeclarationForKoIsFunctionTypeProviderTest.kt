package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeArgumentDeclarationForKoIsFunctionTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-argument-is-not-function-type`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isFunctionType shouldBeEqualTo false
    }

    @Test
    fun `function-type-argument`() {
        // given
        val sut =
            getSnippetFile("function-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isFunctionType shouldBeEqualTo true
    }

    @Test
    fun `typealias-with-function-type-argument`() {
        // given
        val sut =
            getSnippetFile("typealias-with-function-type-argument")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        sut?.isFunctionType shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypeargument/snippet/forkoisfunctiontypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-type-argument"),
                arguments("external-type-argument"),
                arguments("generic-type-argument"),
                arguments("import-alias-type-argument"),
                arguments("interface-type-argument"),
                arguments("kotlin-type-argument"),
                arguments("object-type-argument"),
                arguments("typealias-without-function-type-argument"),
            )
    }
}
