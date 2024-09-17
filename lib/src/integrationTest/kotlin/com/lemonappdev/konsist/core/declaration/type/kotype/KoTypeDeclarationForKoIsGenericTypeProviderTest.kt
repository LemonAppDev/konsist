package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoIsGenericTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `is-generic-type`(
        fileName: String,
        value: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties()
                .first()
                .type

        // then
        sut?.isGenericType shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkoisgenerictypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("kotlin-type", false),
                arguments("generic-type", true),
                arguments("generic-class-type", true),
                arguments("not-generic-class-type", false),
                arguments("interface-type", false),
                arguments("object-type", false),
                arguments("function-type", false),
                arguments("generic-import-alias-type", true),
                arguments("not-generic-import-alias-type", false),
                arguments("generic-typealias-type", true),
                arguments("not-generic-typealias-type", false),
                arguments("external-type", false),
            )
    }
}
