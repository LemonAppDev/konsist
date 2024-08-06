package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoPackageProviderTest {
    @Test
    fun `class-is-not-in-package`() {
        // given
        val sut =
            getSnippetFile("class-is-not-in-package")
                .classes()
                .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `class-is-in-package`() {
        // given
        val sut =
            getSnippetFile("class-is-in-package")
                .classes()
                .first()

        // then
        sut.packagee?.name shouldBeEqualTo "com.samplepackage"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkopackageprovider/", fileName)
}
