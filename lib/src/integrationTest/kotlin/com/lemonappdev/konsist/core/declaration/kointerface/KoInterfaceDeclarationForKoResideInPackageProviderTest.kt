package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `interface-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("interface-not-reside-in-file-package")
                .interfaces()
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `interface-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("interface-reside-in-file-package")
                .interfaces()
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `interface-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("interface-not-reside-outside-file-package")
                .interfaces()
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `interface-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("interface-reside-outside-file-package")
                .interfaces()
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoresideinpackageprovider/", fileName)
}
