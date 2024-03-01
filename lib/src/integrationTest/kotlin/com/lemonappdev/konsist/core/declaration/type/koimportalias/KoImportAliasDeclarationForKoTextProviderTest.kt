package com.lemonappdev.konsist.core.declaration.type.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoImportAliasDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-text`(
        fileName: String,
        value: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type
            ?.declaration as? KoImportAliasDeclaration

        // then
        sut?.text shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/koimportalias/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("import-alias-type-text", "ImportAlias"),
            arguments("nullable-import-alias-type-text", "ImportAlias"),
        )
    }
}
