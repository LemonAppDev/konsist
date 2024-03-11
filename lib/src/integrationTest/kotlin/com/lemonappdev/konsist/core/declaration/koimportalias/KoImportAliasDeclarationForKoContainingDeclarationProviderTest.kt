package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationForKoContainingDeclarationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-containing-declaration`(fileName: String) {
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
        (sut?.containingDeclaration as? KoNameProvider)?.name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koimportalias/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("import-alias-type-containing-declaration"),
                arguments("nullable-import-alias-type-containing-declaration"),
            )
    }
}
