package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `function-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("function-not-reside-in-file-package")
                .functions()
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `function-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("function-reside-in-file-package")
                .functions()
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `function-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("function-not-reside-outside-file-package")
                .functions()
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `function-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("function-reside-outside-file-package")
                .functions()
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkoresideinpackageprovider/", fileName)
}
