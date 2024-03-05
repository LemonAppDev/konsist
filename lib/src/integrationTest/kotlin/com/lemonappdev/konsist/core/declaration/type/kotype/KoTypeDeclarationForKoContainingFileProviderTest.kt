package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoContainingFileProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `containing-file`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.containingFile?.nameWithExtension shouldBeEqualTo "$fileName.kt"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/type/kotype/snippet/forkocontainingfileprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("nullable-kotlin-basic-type-containing-file"),
            arguments("not-nullable-kotlin-basic-type-containing-file"),
            arguments("nullable-kotlin-collection-type-containing-file"),
            arguments("not-nullable-kotlin-collection-type-containing-file"),
            arguments("nullable-class-type-containing-file"),
            arguments("not-nullable-class-type-containing-file"),
            arguments("nullable-interface-type-containing-file"),
            arguments("not-nullable-interface-type-containing-file"),
            arguments("nullable-object-type-containing-file"),
            arguments("not-nullable-object-type-containing-file"),
            arguments("nullable-function-type-containing-file"),
            arguments("not-nullable-function-type-containing-file"),
            arguments("nullable-import-alias-type-containing-file"),
            arguments("not-nullable-import-alias-type-containing-file"),
            arguments("nullable-typealias-type-containing-file"),
            arguments("not-nullable-typealias-type-containing-file"),
            arguments("nullable-external-type-containing-file"),
            arguments("not-nullable-external-type-containing-file"),
        )
    }
}
