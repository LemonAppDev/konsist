package com.lemonappdev.konsist.core.declaration.type.koimportalias

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoImportAliasDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportAliasDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `import-alias-type-reside-in-package`() {
        // given
        val sut = getSnippetFile("import-alias-type-reside-in-package")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type
            ?.declaration as? KoImportAliasDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/koimportalias/snippet/forkoresideinpackageprovider/", fileName)
}
