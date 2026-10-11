package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `property-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("property-not-reside-in-file-package")
                .properties()
                .first()

        // then
        sut.residesInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `property-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("property-reside-in-file-package")
                .properties()
                .first()

        // then
        sut.residesInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `property-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("property-not-reside-outside-file-package")
                .properties()
                .first()

        // then
        sut.residesOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `property-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("property-reside-outside-file-package")
                .properties()
                .first()

        // then
        sut.residesOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoresideinpackageprovider/", fileName)
}
