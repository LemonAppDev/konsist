package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `argument-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("argument-not-reside-in-file-package")
            .classes()
            .first()
            .enumConstants
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `argument-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("argument-reside-in-file-package")
            .classes()
            .first()
            .enumConstants
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `argument-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("argument-not-reside-outside-file-package")
            .classes()
            .first()
            .enumConstants
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `argument-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("argument-reside-outside-file-package")
            .classes()
            .first()
            .enumConstants
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
