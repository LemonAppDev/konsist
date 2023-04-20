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
            resideInPath("main/") shouldBeEqualTo false
            resideInPath("test/") shouldBeEqualTo false
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
            resideInPath("../test/..") shouldBeEqualTo true
            resideInPath("..snippet/forresidemethods/..") shouldBeEqualTo true
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
            resideOutsidePath("../test/..") shouldBeEqualTo false
            resideOutsidePath("..snippet/forresidemethods/..") shouldBeEqualTo false
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
            resideOutsidePath("/test/") shouldBeEqualTo true
            resideOutsidePath("/Main/") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/forresidemethods/", fileName)
}
