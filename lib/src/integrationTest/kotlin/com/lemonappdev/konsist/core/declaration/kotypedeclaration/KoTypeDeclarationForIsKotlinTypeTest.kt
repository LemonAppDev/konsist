package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForIsKotlinTypeTest {
    @ParameterizedTest
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/foriskotlintype/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("is-kotlin-type-string", true),
            arguments("is-kotlin-type-list-of-string", true),
            arguments("is-kotlin-type-list-of-sample-type", true),
            arguments("is-kotlin-type-sample-collection-of-string", true),
            arguments("is-kotlin-type-sample-collection-of-sample-type", false),
        )
    }
}
