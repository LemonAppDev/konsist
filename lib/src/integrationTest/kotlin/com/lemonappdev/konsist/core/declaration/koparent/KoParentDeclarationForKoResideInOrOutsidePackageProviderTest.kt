package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `parent-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-not-reside-in-file-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parent-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("parent-reside-in-file-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parent-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-not-reside-outside-file-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parent-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("parent-reside-outside-file-package")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
