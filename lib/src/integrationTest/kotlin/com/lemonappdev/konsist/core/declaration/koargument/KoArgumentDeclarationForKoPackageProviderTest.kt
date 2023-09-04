package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoPackageProviderTest {
    @Test
    fun `argument-is-not-in-package`() {
        // given
        val sut = getSnippetFile("argument-is-not-in-package")
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
    fun `argument-is-in-package`() {
        // given
        val sut = getSnippetFile("argument-is-in-package")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkopackageprovider/", fileName)
}
