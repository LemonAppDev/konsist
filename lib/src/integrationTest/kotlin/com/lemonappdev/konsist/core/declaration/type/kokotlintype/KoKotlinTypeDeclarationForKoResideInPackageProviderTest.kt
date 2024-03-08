package com.lemonappdev.konsist.core.declaration.type.kokotlintype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKotlinTypeDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `nullable-kotlin-basic-type-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-basic-type-reside-in-file-package")
                .properties()
                .first()
                .type
                ?.sourceDeclaration as? KoKotlinTypeDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPackage("kotlin..") shouldBeEqualTo true
            it?.resideInPackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("kotlin..") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo true
        }
    }

    @Test
    fun `not-nullable-kotlin-basic-type-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-basic-type-reside-in-file-package")
                .properties()
                .first()
                .type
                ?.sourceDeclaration as? KoKotlinTypeDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPackage("kotlin..") shouldBeEqualTo true
            it?.resideInPackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("kotlin..") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo true
        }
    }

    @Test
    fun `nullable-kotlin-collection-type-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-collection-type-reside-in-file-package")
                .properties()
                .first()
                .type
                ?.sourceDeclaration as? KoKotlinTypeDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPackage("kotlin..") shouldBeEqualTo true
            it?.resideInPackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("kotlin..") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo true
        }
    }

    @Test
    fun `not-nullable-kotlin-collection-type-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-collection-type-reside-in-file-package")
                .properties()
                .first()
                .type
                ?.sourceDeclaration as? KoKotlinTypeDeclaration

        // then
        assertSoftly(sut) {
            it?.resideInPackage("kotlin..") shouldBeEqualTo true
            it?.resideInPackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("kotlin..") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kokotlintype/snippet/forkoresideinpackageprovider/", fileName)
}
