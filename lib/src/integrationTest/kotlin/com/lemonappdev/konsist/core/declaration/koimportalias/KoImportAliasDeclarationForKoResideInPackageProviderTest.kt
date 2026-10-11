package com.lemonappdev.konsist.core.declaration.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `import-alias-type-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("import-alias-type-reside-in-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asImportAliasDeclaration()

        // then
        assertSoftly(sut) {
            it?.residesInPackage("com..") shouldBeEqualTo true
            it?.residesInPackage("com") shouldBeEqualTo false
            it?.residesOutsidePackage("com..") shouldBeEqualTo false
            it?.residesOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportalias/snippet/forkoresideinpackageprovider/", fileName)
}
