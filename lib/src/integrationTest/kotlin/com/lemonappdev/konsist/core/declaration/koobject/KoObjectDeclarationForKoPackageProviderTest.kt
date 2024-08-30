package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoPackageProviderTest {
    @Test
    fun `object-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("object-is-not-in-package")
                .objects()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `object-is-in-package`() {
        // given
        val sut =
            getSnippetFile("object-is-in-package")
                .objects()
                .first()

        // then
        sut.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkopackageprovider/", fileName)
}
