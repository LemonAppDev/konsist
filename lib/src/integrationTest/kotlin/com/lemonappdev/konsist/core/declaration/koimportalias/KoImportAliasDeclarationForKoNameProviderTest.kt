package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationForKoNameProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-name`(
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
                ?.sourceImportAlias

        // then
        sut?.name shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forkonameprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("import-alias-type-name", "ImportAlias"),
                arguments("nullable-import-alias-type-name", "ImportAlias"),
            )
    }
}
