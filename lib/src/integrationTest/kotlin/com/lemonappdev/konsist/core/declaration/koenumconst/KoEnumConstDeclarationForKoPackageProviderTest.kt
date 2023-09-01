package com.lemonappdev.konsist.core.declaration.koenumconst

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstDeclarationForKoPackageProviderTest {
    @Test
    fun `enum-const-is-not-in-package`() {
        // given
        val sut = getSnippetFile("enum-const-is-not-in-package")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `enum-const-is-in-package`() {
        // given
        val sut = getSnippetFile("enum-const-is-in-package")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconst/snippet/forkopackageprovider/", fileName)
}
