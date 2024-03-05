package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationForKoLocationProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-location`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type
            ?.sourceDeclaration as? KoImportAliasDeclaration

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:1:52"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forkolocationprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("import-alias-type-location"),
            arguments("nullable-import-alias-type-location"),
        )
    }
}
