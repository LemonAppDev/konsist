package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoGenericTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `is-nullable`(
        fileName: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .properties()
            .first()
            .type

        // then
        sut?.isGenericType shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/forkogenerictypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("generic-type", true),
            arguments("not-generic-type", false),
        )
    }
}
