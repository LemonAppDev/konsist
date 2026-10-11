package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `enum-const-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-not-reside-in-file-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.residesInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `enum-const-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-reside-in-file-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.residesInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `enum-const-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-not-reside-outside-file-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.residesOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `enum-const-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-reside-outside-file-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.residesOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoresideinpackageprovider/", fileName)
}
