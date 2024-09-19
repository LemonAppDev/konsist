package com.lemonappdev.konsist.core.declaration.type.kogenerictype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoGenericTypeDeclarationForKoContainingFileProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-containing-file`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asGenericTypeDeclaration()

        // then
        sut?.containingFile?.nameWithExtension shouldBeEqualTo "$fileName.kt"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kogenerictype/snippet/forkocontainingfileprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("generic-type-containing-file"),
                arguments("nullable-generic-type-containing-file"),
            )
    }
}