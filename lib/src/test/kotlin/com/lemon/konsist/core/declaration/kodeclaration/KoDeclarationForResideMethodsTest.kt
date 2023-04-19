package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
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
        sut.resideInPackages("com") shouldBeEqualTo false
    }

    @Test
    fun `package-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("package-reside-in-file-package")
            .classes()
            .first()

        // then
        sut.resideInPackages("com..") shouldBeEqualTo true
    }

    @Test
    fun `package-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("package-not-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackages("com..") shouldBeEqualTo false
    }

    @Test
    fun `package-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("package-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackages("com") shouldBeEqualTo true
    }

    @Test
    fun `path-not-reside-in-path`() {
        // given
        val sut = getSnippetFile("path-not-reside-in-path")
            .classes()
            .first()

        // then
        sut.run {
            resideInPath("Main/") shouldBeEqualTo false
            resideInPath("TEST/", ignoreCase = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `path-reside-in-path`() {
        // given
        val sut = getSnippetFile("path-reside-in-path")
            .classes()
            .first()

        // then
        sut.run {
            resideInPath("TEST/") shouldBeEqualTo true
            resideInPath("test/", ignoreCase = false) shouldBeEqualTo true
            resideInPath("") shouldBeEqualTo true
        }
    }

    @Test
    fun `path-not-reside-outside-path`() {
        // given
        val sut = getSnippetFile("path-not-reside-outside-path")
            .classes()
            .first()

        // then
        sut.run {
            resideOutsidePath("test/", ignoreCase = false) shouldBeEqualTo false
            resideOutsidePath("TEST/") shouldBeEqualTo false
        }
    }

    @Test
    fun `path-reside-outside-path`() {
        // given
        val sut = getSnippetFile("path-reside-outside-path")
            .classes()
            .first()

        // then
        sut.run {
            resideOutsidePath("TEST/", ignoreCase = false) shouldBeEqualTo true
            resideOutsidePath("Main/") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forresidemethods/", fileName)
}
