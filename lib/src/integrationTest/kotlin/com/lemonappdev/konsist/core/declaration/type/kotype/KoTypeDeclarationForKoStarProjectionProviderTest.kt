package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoStarProjectionProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `not-star-projection`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        sut?.isStarProjection shouldBeEqualTo false
    }

    @Test
    fun `star-projection-type`() {
        // given
        val sut =
            getSnippetFile("star-projection-type")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration

        // then
//        sut?.isStarProjection shouldBeEqualTo true // Todo: ***
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkostarprojectionprovider/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-type"),
                arguments("not-nullable-kotlin-type"),
                arguments("nullable-class-type"),
                arguments("not-nullable-class-type"),
                arguments("nullable-interface-type"),
                arguments("not-nullable-interface-type"),
                arguments("nullable-object-type"),
                arguments("not-nullable-object-type"),
                arguments("nullable-function-type-type"),
                arguments("not-nullable-function-type-type"),
                arguments("nullable-import-alias-type"),
                arguments("not-nullable-import-alias-type"),
                arguments("nullable-typealias-type"),
                arguments("not-nullable-typealias-type"),
                arguments("nullable-type-parameter"),
                arguments("not-nullable-type-parameter"),
                arguments("nullable-external-type"),
                arguments("not-nullable-external-type"),
            )
    }
}
