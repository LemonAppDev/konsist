package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoLocationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `location`(
        fileName: String,
        value: String,
    ) {
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
        sut?.location shouldBeEqualTo "${sut?.path}:$value"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/type/kotype/snippet/forkolocationprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-type-location", "1:41"),
                arguments("not-nullable-kotlin-type-location", "1:41"),
                arguments("nullable-generic-type-location", "1:41"),
                arguments("not-nullable-generic-type-location", "1:41"),
                arguments("nullable-class-type-location", "3:41"),
                arguments("not-nullable-class-type-location", "3:41"),
                arguments("nullable-interface-type-location", "3:41"),
                arguments("not-nullable-interface-type-location", "3:41"),
                arguments("nullable-object-type-location", "3:41"),
                arguments("not-nullable-object-type-location", "3:41"),
                arguments("nullable-function-type-location", "3:41"),
                arguments("not-nullable-function-type-location", "3:41"),
                arguments("nullable-import-alias-type-location", "3:41"),
                arguments("not-nullable-import-alias-type-location", "3:41"),
                arguments("nullable-typealias-type-location", "3:41"),
                arguments("not-nullable-typealias-type-location", "3:41"),
                arguments("nullable-external-type-location", "3:41"),
                arguments("not-nullable-external-type-location", "3:41"),
            )
    }
}
