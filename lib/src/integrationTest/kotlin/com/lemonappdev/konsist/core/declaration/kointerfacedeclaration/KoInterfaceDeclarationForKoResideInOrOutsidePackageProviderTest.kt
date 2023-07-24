package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `interface-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("interface-not-reside-in-file-package")
            .interfaces()
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `interface-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("interface-reside-in-file-package")
            .interfaces()
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `interface-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("interface-not-reside-outside-file-package")
            .interfaces()
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `interface-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("interface-reside-outside-file-package")
            .interfaces()
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
