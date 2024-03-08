package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `object-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("object-not-reside-in-file-package")
                .objects()
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `object-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("object-reside-in-file-package")
                .objects()
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `object-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("object-not-reside-outside-file-package")
                .objects()
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `object-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("object-reside-outside-file-package")
                .objects()
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoresideinpackageprovider/", fileName)
}
