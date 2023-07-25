package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `secondary-constructor-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("secondary-constructor-not-reside-in-file-package")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `secondary-constructor-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("secondary-constructor-reside-in-file-package")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("secondary-constructor-not-reside-outside-file-package")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `secondary-constructor-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("secondary-constructor-reside-outside-file-package")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
