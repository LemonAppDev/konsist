package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoIsMutableTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `is-mutable-type`(
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
        sut?.isMutableType shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotype/snippet/forkoismutabletypeprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("kotlin-type-is-mutable-type", true),
                arguments("kotlin-type-is-not-mutable-type", false),
                arguments("generic-type-is-mutable-type", true),
                arguments("generic-type-is-not-mutable-type", false),
                arguments("class-type-is-mutable-type", true),
                arguments("class-type-is-not-mutable-type", false),
                arguments("interface-type-is-mutable-type", true),
                arguments("interface-type-is-not-mutable-type", false),
                arguments("object-type-is-mutable-type", true),
                arguments("object-type-is-not-mutable-type", false),
                arguments("function-type-type-is-not-mutable-type", false),
                arguments("import-alias-type-is-mutable-type", true),
                arguments("import-alias-type-is-not-mutable-type", false),
                arguments("typealias-type-is-mutable-type", true),
                arguments("typealias-type-is-not-mutable-type", false),
                arguments("external-type-is-mutable-type", true),
                arguments("external-type-is-not-mutable-type", false),
            )
    }
}
