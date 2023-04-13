package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForResideMethodsTest {
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

    @Test
    fun `none-package-reside-outside-file-package`() {
        // given
        val sut = getSut("none-package-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackages("com..", "com.domain.update.usecase") shouldBeEqualTo false
    }

    @Test
    fun `all-packages-reside-outside-file-package`() {
        // given
        val sut = getSut("all-packages-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackages("com", "com.domain.update") shouldBeEqualTo true
    }

    @Test
    fun `one-package-reside-outside-file-package`() {
        // given
        val sut = getSut("one-package-reside-outside-file-package")
            .classes()
            .first()

        // then
        sut.resideOutsidePackages("com..", "com") shouldBeEqualTo false
    }

    @Test
    fun `none-path-reside-in-path`() {
        // given
        val sut = getSut("none-path-reside-in-path")
            .classes()
            .first()

        // then
        sut.run {
            resideInPath("Main/", "Any/") shouldBeEqualTo false
            resideInPath("Main/", "TEST/", ignoreCase = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `all-paths-reside-in-path`() {
        // given
        val sut = getSut("all-paths-reside-in-path")
            .classes()
            .first()

        // then
        sut.run {
            resideInPath("Test/", "TEST/") shouldBeEqualTo true
            resideInPath("", "Test/", ignoreCase = false) shouldBeEqualTo true
            resideInPath("", "TEST/") shouldBeEqualTo true
        }
    }

    @Test
    fun `one-path-reside-in-path`() {
        // given
        val sut = getSut("one-path-reside-in-path")
            .classes()
            .first()

        // then
        sut.run {
            resideInPath("Test/", "Main/") shouldBeEqualTo true
            resideInPath("TEST/") shouldBeEqualTo true
            resideInPath("TEST/", ignoreCase = false) shouldBeEqualTo false
            resideInPath("Main/") shouldBeEqualTo false
            resideInPath("") shouldBeEqualTo true
        }
    }

    @Test
    fun `none-path-reside-outside-path`() {
        // given
        val sut = getSut("none-path-reside-outside-path")
            .classes()
            .first()

        // then
        sut.run {
            resideOutsidePath("Test/", "", ignoreCase = false) shouldBeEqualTo false
            resideOutsidePath("Test/", "TEST/") shouldBeEqualTo false
        }
    }

    @Test
    fun `all-paths-reside-outside-path`() {
        // given
        val sut = getSut("all-paths-reside-outside-path")
            .classes()
            .first()

        // then
        sut.run {
            resideOutsidePath("Main/", "TEST/", ignoreCase = false) shouldBeEqualTo true
            resideOutsidePath("Main/", "Any/") shouldBeEqualTo true
        }
    }

    @Test
    fun `one-path-reside-outside-path`() {
        // given
        val sut = getSut("one-path-reside-outside-path")
            .classes()
            .first()

        // then
        sut.run {
            resideOutsidePath("Main/", "Test/") shouldBeEqualTo false
            resideOutsidePath("Main/") shouldBeEqualTo true
            resideOutsidePath("Main/", ignoreCase = false) shouldBeEqualTo true
            resideOutsidePath("MAIN/") shouldBeEqualTo true
            resideOutsidePath("MAIN/", ignoreCase = false) shouldBeEqualTo true
            resideOutsidePath("Test/") shouldBeEqualTo false
            resideOutsidePath("TEST/") shouldBeEqualTo false
            resideOutsidePath("TEST/", ignoreCase = false) shouldBeEqualTo true
            resideOutsidePath("") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forresidemethods/", fileName)
}
