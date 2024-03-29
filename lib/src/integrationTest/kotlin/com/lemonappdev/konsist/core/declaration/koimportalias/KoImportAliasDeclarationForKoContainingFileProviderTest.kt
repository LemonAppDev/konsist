package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationForKoContainingFileProviderTest {
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
                ?.asImportAliasDeclaration()

        // then
        sut?.containingFile?.nameWithExtension shouldBeEqualTo "$fileName.kt"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forkocontainingfileprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("import-alias-type-containing-file"),
                arguments("nullable-import-alias-type-containing-file"),
            )
    }
}
