package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
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
                ?.sourceDeclaration as? KoImportAliasDeclaration

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
                ?.sourceDeclaration as? KoImportAliasDeclaration

        // then
        sut?.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forkopackageprovider/", fileName)
}
