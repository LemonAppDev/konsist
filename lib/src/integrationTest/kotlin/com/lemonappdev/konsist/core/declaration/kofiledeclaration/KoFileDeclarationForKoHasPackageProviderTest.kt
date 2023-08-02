package com.lemonappdev.konsist.core.declaration.kofiledeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoHasPackageProviderTest {
    @Test
    fun `file-has-no-package`() {
        // given
        val sut = getSnippetFile("file-has-no-package")
            .files
            .first()

        // then
        sut.hasPackage("com..") shouldBeEqualTo false
    }

    @Test
    fun `file-has-package`() {
        // given
        val sut = getSnippetFile("file-has-package")
            .files
            .first()

        // then
        assertSoftly(sut) {
            hasPackage("com.samplepackage") shouldBeEqualTo true
            hasPackage("com..") shouldBeEqualTo true
            hasPackage("com") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofiledeclaration/snippet/forkohaspackageprovider/",
        fileName,
    )
}
