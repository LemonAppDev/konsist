package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoContainingDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `parent-declaration`(fileName: String) {
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
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "sampleProperty1"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/type/kotype/snippet/forkocontainingdeclarationprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("nullable-kotlin-basic-type-containing-declaration"),
                arguments("not-nullable-kotlin-basic-type-containing-declaration"),
                arguments("nullable-kotlin-collection-type-containing-declaration"),
                arguments("not-nullable-kotlin-collection-type-containing-declaration"),
                arguments("nullable-class-type-containing-declaration"),
                arguments("not-nullable-class-type-containing-declaration"),
                arguments("nullable-interface-type-containing-declaration"),
                arguments("not-nullable-interface-type-containing-declaration"),
                arguments("nullable-object-type-containing-declaration"),
                arguments("not-nullable-object-type-containing-declaration"),
                arguments("nullable-function-type-containing-declaration"),
                arguments("not-nullable-function-type-containing-declaration"),
                arguments("nullable-import-alias-type-containing-declaration"),
                arguments("not-nullable-import-alias-type-containing-declaration"),
                arguments("nullable-typealias-type-containing-declaration"),
                arguments("not-nullable-typealias-type-containing-declaration"),
                arguments("nullable-external-type-containing-declaration"),
                arguments("not-nullable-external-type-containing-declaration"),
            )
    }
}
