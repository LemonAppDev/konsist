package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoPackageMatchingPathProviderTest {
    @Test
    fun `package-with-matching-file-path`() {
        // given
        val sut =
            getSnippetFile("package-with-matching-file-path")
                .packages
                .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo true
    }

    @Test
    fun `package-without-matching-file-path`() {
        // given
        val sut =
            getSnippetFile("package-without-matching-file-path")
                .packages
                .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo false
    }

    @Test
    fun `package-with-package-matching-parent-directory`() {
        // given
        val sut =
            getSnippetFile("package-with-package-matching-parent-directory")
                .packages
                .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo false
    }

    @Test
    fun `package-with-package-matching-directory-name-prefix`() {
        // given
        val sut =
            getSnippetFile("package-with-package-matching-directory-name-prefix")
                .packages
                .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo false
    }

    @Test
    fun `package-with-one-segment-package-matching-directory-name-suffix`() {
        // given
        val sut =
            getSnippetFile("package-with-one-segment-package-matching-directory-name-suffix")
                .packages
                .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo false
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopackage/snippet/forkomatchingpathprovider/", fileName)
}
