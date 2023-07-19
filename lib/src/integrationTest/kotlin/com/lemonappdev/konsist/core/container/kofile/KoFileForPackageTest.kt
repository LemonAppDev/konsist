package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPackageTest {
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
            packagee?.name shouldBeEqualTo "samplepackage"
            hasPackage("com.samplepackage") shouldBeEqualTo true
            hasPackage("com..") shouldBeEqualTo true
            hasPackage("com") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forpackage/", fileName)
}
