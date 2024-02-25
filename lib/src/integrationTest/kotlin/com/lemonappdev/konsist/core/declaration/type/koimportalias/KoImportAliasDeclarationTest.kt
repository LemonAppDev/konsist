package com.lemonappdev.konsist.core.declaration.type.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoImportAliasDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoImportAliasDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut = getSnippetFile("type-to-string")
            .properties()
            .first()
            .type

        // then
        sut.toString() shouldBeEqualTo "ImportAlias"
    }

    @Test
    fun `import-directive`() {
        // given
        val type = getSnippetFile("import-directive")
            .properties()
            .first()
            .type

        val sut = type as? KoImportAliasDeclaration

        // then
        sut?.importDirective?.name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/koimportalias/snippet/forgeneral/", fileName)
}
