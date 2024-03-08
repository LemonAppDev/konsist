package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut =
            getSnippetFile("type-to-string")
                .properties()
                .first()
                .type
                ?.sourceDeclaration as? KoImportAliasDeclaration

        // then
        sut.toString() shouldBeEqualTo "ImportAlias"
    }

    @Test
    fun `import-directive`() {
        // given
        val sut =
            getSnippetFile("import-directive")
                .properties()
                .first()
                .type
                ?.sourceDeclaration as? KoImportAliasDeclaration

        // then
        sut?.importDirective?.name shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forgeneral/", fileName)
}
