package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasDeclarationForKoPackageProviderTest {
    @Test
    fun `import-alias-type-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-is-not-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asImportAliasDeclaration()

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @Test
    fun `import-alias-type-is-in-package`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-is-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asImportAliasDeclaration()

        // then
        sut?.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forkopackageprovider/", fileName)
}
