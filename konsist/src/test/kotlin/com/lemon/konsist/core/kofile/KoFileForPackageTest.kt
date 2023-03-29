package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPackageTest {
    @Test
    fun `file-with-one-package`() {
        // given
        val sut =
            getSut("file-with-one-package")
                .files()
                .first()

        // then
        sut.packageDirective?.name shouldBeEqualTo "SamplePackage"
    }

    @Test
    fun `file-without-package`() {
        // given
        val sut =
            getSut("file-without-package")
                .files()
                .first()

        // then
        sut.packageDirective shouldBeEqualTo null
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forpackage/$fileName.kt.txt")
}
