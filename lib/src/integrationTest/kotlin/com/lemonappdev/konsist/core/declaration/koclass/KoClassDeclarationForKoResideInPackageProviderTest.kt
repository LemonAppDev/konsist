package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `class-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("class-not-reside-in-file-package")
                .classes()
                .first()

        // then
        sut.residesInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `class-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("class-reside-in-file-package")
                .classes()
                .first()

        // then
        sut.residesInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `class-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("class-not-reside-outside-file-package")
                .classes()
                .first()

        // then
        sut.residesOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `class-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("class-reside-outside-file-package")
                .classes()
                .first()

        // then
        sut.residesOutsidePackage("com") shouldBeEqualTo true
    }

    @Suppress("DEPRECATION")
    @Test
    fun `deprecated resideInPackage and resideOutsidePackage delegate to new methods`() {
        // given
        val sut =
            getSnippetFile("class-reside-in-file-package")
                .classes()
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoresideinpackageprovider/", fileName)
}
