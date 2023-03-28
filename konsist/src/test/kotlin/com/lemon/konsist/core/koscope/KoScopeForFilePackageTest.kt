package com.lemon.konsist.core.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForFilePackageTest {

    @Test
    fun `file-with-package`() {
        // given
        val sut = getSut("file-with-package")

        // then
        sut.packages().map { it?.name } shouldBeEqualTo listOf("samplepackage")
    }

    @Test
    fun `file-without-package`() {
        // given
        val sut = getSut("file-without-package")

        // then
        sut.packages().isEmpty()
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/forfilepackage/$fileName.kt.txt")
}
