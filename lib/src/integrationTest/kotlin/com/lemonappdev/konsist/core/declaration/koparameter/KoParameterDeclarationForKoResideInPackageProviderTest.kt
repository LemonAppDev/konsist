package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `parameter-in-constructor-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-not-reside-in-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parameter-in-function-invocation-not-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-not-reside-in-file-package")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `parameter-in-constructor-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-reside-in-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parameter-in-function-invocation-reside-in-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-reside-in-file-package")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `parameter-in-constructor-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-not-reside-outside-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parameter-in-function-invocation-not-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-not-reside-outside-file-package")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `parameter-in-constructor-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-reside-outside-file-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.resideOutsidePackage("com") shouldBeEqualTo true
    }

    @Test
    fun `parameter-in-function-invocation-reside-outside-file-package`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-reside-outside-file-package")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkoresideinpackageprovider/", fileName)
}
