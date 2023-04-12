package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForResideInPackagesTest {
    @Test
    fun `none-package-reside-in-file-package`() {
        // given
        val sut = getSut("none-package-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackages("com", "..nonexisting..") shouldBeEqualTo false
    }

    @Test
    fun `all-packages-reside-in-file-package`() {
        // given
        val sut = getSut("all-packages-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackages("com..", "com.domain.update.usecase") shouldBeEqualTo true
    }

    @Test
    fun `one-package-reside-in-file-package`() {
        // given
        val sut = getSut("one-package-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackages("com..", "com") shouldBeEqualTo true
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kodeclaration/snippet/forresideinpackage/", fileName)
}
