package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `parameter-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-not-reside-in-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parameter-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-reside-in-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parameter-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-not-reside-outside-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parameter-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-reside-outside-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkoresideinpackageprovider/", fileName)
}
