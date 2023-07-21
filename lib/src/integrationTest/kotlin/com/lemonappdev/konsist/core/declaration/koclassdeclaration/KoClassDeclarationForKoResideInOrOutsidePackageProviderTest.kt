package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `class-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("class-not-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `class-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("class-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `class-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("class-not-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `class-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("class-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
