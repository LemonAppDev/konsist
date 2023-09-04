package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoPackageProviderTest {
    @Test
    fun `argument-in-enum-const-is-not-in-package`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-is-not-in-package")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `argument-in-enum-const-is-in-package`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-is-in-package")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `argument-in-annotation-is-not-in-package`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-is-not-in-package")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `argument-in-annotation-is-in-package`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-is-in-package")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkopackageprovider/", fileName)
}
