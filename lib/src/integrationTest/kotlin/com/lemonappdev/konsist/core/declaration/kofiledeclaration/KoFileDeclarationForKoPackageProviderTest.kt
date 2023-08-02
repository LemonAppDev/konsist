package com.lemonappdev.konsist.core.declaration.kofiledeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoPackageProviderTest {
    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSnippetFile("file-contains-no-package")
            .files
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSnippetFile("file-contains-package")
            .files
            .first()

        // then
        assertSoftly(sut) {
            packagee shouldNotBeEqualTo null
            packagee?.name shouldBeEqualTo "samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofiledeclaration/snippet/forkopackageprovider/",
        fileName,
    )
}
