package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `constructor-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("constructor-not-reside-in-file-package")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `constructor-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("constructor-reside-in-file-package")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `constructor-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("constructor-not-reside-outside-file-package")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `constructor-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("constructor-reside-outside-file-package")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructor/snippet/forkoresideinpackageprovider/", fileName)
}
