package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForResideMethodsTest {
    @Test
    fun `package-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("package-not-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `package-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("package-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `package-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("package-not-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `package-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("package-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forresidemethods/", fileName)
}
