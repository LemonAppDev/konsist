package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoIsFunctionTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-is-not-function-type`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .properties()
                .first()
                .type

        // then
        sut?.isFunctionType shouldBeEqualTo false
    }

    @Test
    fun `type-is-function-type`() {
        // given
        val sut =
            getSnippetFile("type-is-function-type")
                .properties()
                .first()
                .type

        // then
        sut?.isFunctionType shouldBeEqualTo true
    }

    @Test
    fun `typealias-with-function-type`() {
        // given
        val sut =
            getSnippetFile("typealias-with-function-type")
                .properties()
                .first()
                .type

        // then
        sut?.isFunctionType shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkoisfunctiontypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("kotlin-type"),
                arguments("generic-type"),
                arguments("generic-class-type"),
                arguments("not-generic-class-type"),
                arguments("interface-type"),
                arguments("object-type"),
                arguments("generic-import-alias-type"),
                arguments("not-generic-import-alias-type"),
                arguments("generic-typealias-type"),
                arguments("external-type"),
            )
    }
}
