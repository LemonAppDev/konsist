package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationForKoResideInOrOutsidePackageProviderTest {
    @Test
    fun `primary-constructor-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("primary-constructor-not-reside-in-file-package")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("primary-constructor-reside-in-file-package")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("primary-constructor-not-reside-outside-file-package")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `primary-constructor-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("primary-constructor-reside-outside-file-package")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkoresideinoroutsidepackageprovider/", fileName)
}
