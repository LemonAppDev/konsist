package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoIsNullableProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `is-nullable`(
        fileName: String,
        value: Boolean,
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
        sut?.isNullable shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkoisnullableprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("kotlin-type-is-nullable", true),
                arguments("kotlin-type-is-not-nullable", false),
                arguments("generic-type-is-nullable", true),
                arguments("generic-type-is-not-nullable", false),
                arguments("class-type-is-nullable", true),
                arguments("class-type-is-not-nullable", false),
                arguments("interface-type-is-nullable", true),
                arguments("interface-type-is-not-nullable", false),
                arguments("object-type-is-nullable", true),
                arguments("object-type-is-not-nullable", false),
                arguments("function-type-type-is-nullable", true),
                arguments("function-type-type-is-not-nullable", false),
                arguments("import-alias-type-is-nullable", true),
                arguments("import-alias-type-is-not-nullable", false),
                arguments("typealias-type-is-nullable", true),
                arguments("typealias-type-is-not-nullable", false),
                arguments("type-parameter-is-nullable", true),
                arguments("type-parameter-is-not-nullable", false),
                arguments("external-type-is-nullable", true),
                arguments("external-type-is-not-nullable", false),
            )
    }
}
