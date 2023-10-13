package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `variable-not-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("variable-not-reside-in-file-package")
            .functions()
            .variables
            .first()

        // then
        sut.resideInPackage("com") shouldBeEqualTo false
    }

    @Test
    fun `variable-reside-in-file-package`() {
        // given
        val sut = getSnippetFile("variable-reside-in-file-package")
            .functions()
            .variables
            .first()

        // then
        sut.resideInPackage("com..") shouldBeEqualTo true
    }

    @Test
    fun `variable-not-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("variable-not-reside-outside-file-package")
            .functions()
            .variables
            .first()

        // then
        sut.resideOutsidePackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `variable-reside-outside-file-package`() {
        // given
        val sut = getSnippetFile("variable-reside-outside-file-package")
            .functions()
            .variables
            .first()

        // then
        sut.resideOutsidePackage("com") shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkoresideinpackageprovider/", fileName)
}
