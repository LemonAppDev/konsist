package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `typealias-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("typealias-not-reside-in-file-package")
                .typeAliases
                .first()

        // then
        sut.residesInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `typealias-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("typealias-reside-in-file-package")
                .typeAliases
                .first()

        // then
        sut.residesInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `typealias-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("typealias-not-reside-outside-file-package")
                .typeAliases
                .first()

        // then
        sut.residesOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `typealias-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("typealias-reside-outside-file-package")
                .typeAliases
                .first()

        // then
        sut.residesOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealias/snippet/forkoresideinpackageprovider/", fileName)
}
